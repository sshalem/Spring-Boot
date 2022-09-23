import './booksSideBar.js';
import { api } from './booksUrl.js';
import { getBooksAndPaginate, getNumberOfBooks } from './booksPagination.js';
import { jewishDays, getElement } from './utils.js';

// const btnGetByBookname = getElement('.btnGetByBookname');
const btnGetAll = getElement('#nav-all-books');
const booksPerPage = getElement('#current-books-per-page');
const dropdownContent = getElement('.dropdown-content');

/****************************************************************
 *       fixed Header + Section Contetn (Click buttons          *
 ****************************************************************/
const header = getElement('#header');
// const headerOffset = header.getBoundingClientRect().height;
const headerOffset = header.offsetTop;

window.addEventListener('scroll', function () {
    if (window.pageYOffset > headerOffset) {
        header.classList.add('header__fixed-position');
    } else {
        header.classList.remove('header__fixed-position');
    }
});

/********************************************
 *    set date used by footer element       *
 ********************************************/
const dateFooter = getElement('#date');
dateFooter.innerHTML = new Date().getFullYear();
const dateTitle = getElement('.header__date');

/***************************************************************************************
 * Jewish calendar REST API
 * https://www.hebcal.com/
 * https://www.hebcal.com/converter/?cfg=json&gy="
 * https://www.hebcal.com/home/195/jewish-calendar-rest-api
 * const dateUrl = 'https://www.hebcal.com/converter?cfg=json&gy=2021&gm=11&gd=18&g2h=1';
 ****************************************************************************************/

const dateUrl = 'https://www.hebcal.com/converter?cfg=json';
fetch(dateUrl)
    .then((data) => data.json())
    .then((res) => {
        const date = new Date();
        dateTitle.innerHTML = `
            ${jewishDays[date.getDay()]} ,
            ${res.hebrew}  |
            ${date.getDate()}.${date.getMonth() + 1}.${date.getFullYear()}
            `;
    });

/****************************************************************
 * Once we logout I want to clear all data inside sessionStorage
 ****************************************************************/
window.addEventListener(
    'unload',
    () => {
        console.log('sessionStorage Cleared');
        sessionStorage.clear();
    },
    false
);

/********************************************************************
 *  In this code , after the page loaded ,
 *  I check the userId from the header , and sent a Request to fetch
 *  User Details
 * ******************************************************************/
fetch(location).then((res) => {
    // console.log("i'm in fetch(location) method");
    for (let header of res.headers.entries()) {
        // all headers return as small lettes from server
        // console.log(header);
        if (header[0] === 'userid') {
            let userId = header[1];
            fetch(`${api.API_GET_USER_BY_ID}/${userId}`)
                .then((res) => res.json())
                .then((data) => {
                    const user = getElement('#header__logged-user');
                    user.innerHTML = data.username;
                });
        }
    }
});

window.addEventListener('load', () => {
    // Because getNumberOfBooks() returns a promise , thus it's asynchronious
    // If I wont add the setTimeout for at least of 30ms , i won't be able to see
    // the pagination ,since I atill won't have in the sessionStorage the value of 'numberOfRecord'
    // Thus i add a dealy of 50ms , and after this time the updatePageWithBooks() will be
    // Able to use the sessionStorage
    getNumberOfBooks(api.API_GET_NUMBER_OF_BOOK_RECORDS);
    setTimeout(() => {
        getBooksAndPaginate();
    }, 100);
    booksPerPage.innerHTML = `10 ספרים בעמוד`;
});

/*********************************
 * Get ALL Books - Pagination
 *********************************/
btnGetAll.addEventListener('click', function () {
    // I define this line because :
    // if I scroll down , and click again to get books, I want to be on
    // on top of the page
    window.scrollTo(0, 0);
    updatePageWithBooks();
});

/*********************************
 * Books Per Page
 *********************************/
booksPerPage.addEventListener('click', () => {
    dropdownContent.classList.toggle('show');
});

// GetByBookname
// btnGetByBookname.addEventListener('click', function () {
//     const bookname = document.getElementById('inputGetByBookname').value;
//     const url = `${API_GET_BY_BOOKNAME}/${bookname}_requested`;
//     executGet(url);
// });

// function executGet(url) {
//     const options = {
//         credentials: 'include',
//     };
//     fetch(url, options)
//         .then((res) => res.json())
//         .then((data) => {
//             console.log(data);
//             if (data.status === 500) {
//                 const articleError = document.getElementById('errorGetById');
//                 const errorMsg = document.querySelector('.errorMessage');
//                 errorMsg.innerHTML = `${data.message}`;
//                 articleError.classList.remove('visible');
//                 setTimeout(() => {
//                     // errorMsg.innerHTML = `${data.message}`;
//                     articleError.classList.toggle('visible');
//                 }, 3000);
//             } else {
//                 const bookList = document.getElementById('book-list');
//                 bookList.innerHTML = '';
//                 displayDataOnPage(bookList, data);
//             }
//         });
// }
