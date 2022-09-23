import { bookArray } from './bookImages.js';
import { jewishDays } from './utils.js';

const API_GET_USER_BY_ID = `http://localhost:8080/api/users/get/userId`;
const API_URL_GET_ALL_BOOKS = `http://localhost:8080/books/getAll`;
const API_GET_BY_BOOKNAME = `http://localhost:8080/books/get`;
const API_GET_BOOKS_PER_PAGE = `http://localhost:8080/books/getBooksPerPage`;
const API_GET_NUMBER_OF_BOOK_RECORDS = `http://localhost:8080/books/get/numberOfRecords`;

const btnGetByBookname = document.querySelector('.btnGetByBookname');
const btnGetAll = document.getElementById('nav-all-books');

/*
 ***********************************************************************************
 **************** fixed Header + Section Contetn (Click buttons ********************
 ***********************************************************************************
 */

const header = document.getElementById('header');
// const headerOffset = header.getBoundingClientRect().height;
const headerOffset = header.offsetTop;

window.addEventListener('scroll', function () {
    if (window.pageYOffset > headerOffset) {
        header.classList.add('header__fixed-position');
    } else {
        header.classList.remove('header__fixed-position');
    }
});

/*
 ***** set date used by footer element *******
 */
const dateFooter = document.getElementById('date');
dateFooter.innerHTML = new Date().getFullYear();

const dateTitle = document.querySelector('.header__date');

/***************************************************************************************
 * Jewish calendar REST API
 * https://www.hebcal.com/
 * https://www.hebcal.com/converter/?cfg=json&gy="
 * https://www.hebcal.com/home/195/jewish-calendar-rest-api
 * const dateUrl = 'https://www.hebcal.com/converter?cfg=json&gy=2021&gm=11&gd=18&g2h=1';
 ****************************************************************/

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

/**********************************************
 *  In this code , after the page loaded ,
 *  I heck the userId from the header , and sent a Request to fetch
 *  User Details
//  * *************************************/
fetch(location).then((res) => {
    // console.log("i'm in fetch(location) method");
    for (let header of res.headers.entries()) {
        // all headers return as small lettes from server
        // console.log(header);
        if (header[0] === 'userid') {
            let userId = header[1];
            fetch(`${API_GET_USER_BY_ID}/${userId}`)
                .then((res) => res.json())
                .then((data) => {
                    const user = document.getElementById('header__logged-user');
                    user.innerHTML = data.username;
                });
        }
    }
    getNumberOfBooks(API_GET_NUMBER_OF_BOOK_RECORDS);
});

window.addEventListener('load', updatePageWithBooks);

// GetByBookname
// btnGetByBookname.addEventListener('click', function () {
//     const bookname = document.getElementById('inputGetByBookname').value;
//     const url = `${API_GET_BY_BOOKNAME}/${bookname}_requested`;
//     executGet(url);
// });

/*********************************
 * Get ALL Books - Pagination
 *********************************/
btnGetAll.addEventListener('click', function () {
    // I define this line because :
    // if I scroll down , and click again to get books, I want to be on
    // on top of the page
    window.scrollTo(0, 0);
    getNumberOfBooks(API_GET_NUMBER_OF_BOOK_RECORDS);
    updatePageWithBooks();
});

/*******************************
// code for paging books at page
 *******************************/
function updatePageWithBooks() {
    let numberOfRecord = 195;
    let size = 10; // books Per Page
    let index = 1;

    // calculate number of pages (which is also the last page)
    let pages = Math.ceil(numberOfRecord / size);

    const booksContainer = document.querySelector('.books-container');
    let paginationContainer = document.querySelector('.pagination-container');

    async function setupUI() {
        let books = await fetchBooks(index, size);
        displayBooks(booksContainer, books);
        displayPages(paginationContainer, pages, index);
    }

    paginationContainer.addEventListener('click', function (event) {
        if (event.target.classList.contains('pagination-container')) return;
        if (event.target.classList.contains('page-btn')) {
            index = parseInt(event.target.dataset.index);
        }
        if (event.target.classList.contains('fa-angle-double-left')) {
            index = 1;
        }
        if (event.target.classList.contains('fa-angle-left')) {
            index--;
            if (index < 1) {
                index = 1;
            }
        }
        if (event.target.classList.contains('fa-angle-right')) {
            index++;
            if (index > pages) {
                index = pages;
            }
        }
        if (event.target.classList.contains('fa-angle-double-right')) {
            index = pages; // number of pages is also number of last page
        }
        setupUI();
    });

    setupUI();
}

async function fetchBooks(page = 1, size = 20) {
    const options = {
        credentials: 'include',
    };
    const url = `${API_GET_BOOKS_PER_PAGE}?page=${page}&size=${size}&`;
    const response = await fetch(url, options);
    const data = await response.json();
    return data;
}

function displayBooks(container, books) {
    container.innerHTML = '';
    books.forEach((book) => {
        //this filters the image name and return the corrsponding url of the image
        let bookImgToDisplay = bookArray.filter((bookImg) => {
            return book.bookImageUrl == bookImg.bookname;
        });
        container.innerHTML += `
                <article class="book">
                    <div class="book-data">${book.author}</div>
                    <div class="book-data">${book.bookname}</div>
                    <img src="${bookImgToDisplay[0].imageUrl}" alt='_book'/>
                </article>
            `;
    });
    console.log(books);
}

function displayPages(container, pages, activeIndex) {
    container.innerHTML = '';
    container.innerHTML += `                            
                    <i class="fas fa-angle-double-left"></i>
                    <i class="fas fa-angle-left"></i>                
            `;

    if (pages <= 7) {
        for (let pageIndex = 1; pageIndex < pages + 1; pageIndex++) {
            container.innerHTML += `
           <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
            ${pageIndex}
        </button>`;
        }
    } else if (pages > 7) {
        if (activeIndex < 5) {
            for (let pageIndex = 1; pageIndex <= 7; pageIndex++) {
                container.innerHTML += `
                <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
                    ${pageIndex}
                </button>`;
            }
        } else if (activeIndex + 3 > pages) {
            for (let pageIndex = pages - 6; pageIndex <= pages; pageIndex++) {
                container.innerHTML += `
               <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
                    ${pageIndex}
                </button>`;
            }
        } else {
            for (let pageIndex = activeIndex - 3; pageIndex < activeIndex + 4; pageIndex++) {
                container.innerHTML += `
               <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
                    ${pageIndex}
                </button>`;
            }
        }
    }

    container.innerHTML += `             
                <i class="fas fa-angle-right"></i>    
                <i class="fas fa-angle-double-right"></i>                           
            `;
}

/****************************************
 * displayBooksOnPage() - helper method
 ****************************************/

function getNumberOfBooks(url) {
    fetch(url)
        .then((res) => res.json())
        .then((data) => {
            sessionStorage.setItem('numberOfRecord', data);
        });
}

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
