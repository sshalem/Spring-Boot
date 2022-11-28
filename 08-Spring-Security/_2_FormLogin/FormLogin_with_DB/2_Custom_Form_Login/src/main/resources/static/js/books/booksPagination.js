import { bookArray } from './bookImages.js';
import { api } from './booksUrl.js';
import { getElement } from './utils.js';

// https://www.smashingmagazine.com/2007/11/pagination-gallery-examples-and-good-practices/

/*******************************
// code for paging books at page
 *******************************/
export function getBooksAndPaginate() {
    let numberOfRecord = sessionStorage.getItem('numberOfRecord');
    let size = 10; // books Per Page
    let index = 1;

    // calculate number of pages (which is also the last page)
    let pages = Math.ceil(numberOfRecord / size);

    const booksContainer = getElement('.books-container');
    let pagination = getElement('.pagination');

    async function setupUI() {
        let books = await fetchBooks(index, size);
        displayBooks(booksContainer, books);
        displayPages(pagination, pages, index);
    }

    pagination.addEventListener('click', function (event) {
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

/****************************************
 * fetch books from Server
 ****************************************/
async function fetchBooks(page = 1, size = 20) {
    const options = {
        credentials: 'include',
    };
    const url = `${api.API_GET_BOOKS_PER_PAGE}?page=${page}&size=${size}&`;
    const response = await fetch(url, options);
    const data = await response.json();
    return data;
}

/****************************************
 * display Books
 ****************************************/
function displayBooks(container, books) {
    container.innerHTML = '';
    books.forEach((book) => {
        //this filters the image name and return the corrsponding url of the image
        let bookImgToDisplay = bookArray.filter((bookObject) => {
            return book.bookImageUrl == bookObject.bookname;
        });
        container.innerHTML += `
                <article class="book">
                    <div class="book-data">${book.author}</div>
                    <div class="book-data">${book.bookname}</div>
                    <img src="${bookImgToDisplay[0].imageUrl}" alt='_book'/>
                </article>
            `;
    });
    // console.log(books);
}

/****************************************
 * display Pages
 ****************************************/
function displayPages(pagination, pages, activeIndex) {
    pagination.innerHTML = '';
    pagination.innerHTML += `                            
                    <i class="fas fa-angle-double-left"></i>
                    <i class="fas fa-angle-left"></i>                
            `;

    if (pages <= 7) {
        for (let pageIndex = 1; pageIndex < pages + 1; pageIndex++) {
            pagination.innerHTML += `
           <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
            ${pageIndex}
        </button>`;
        }
    } else if (pages > 7) {
        if (activeIndex < 5) {
            for (let pageIndex = 1; pageIndex <= 7; pageIndex++) {
                pagination.innerHTML += `
                <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
                    ${pageIndex}
                </button>`;
            }
        } else if (activeIndex + 3 > pages) {
            for (let pageIndex = pages - 6; pageIndex <= pages; pageIndex++) {
                pagination.innerHTML += `
               <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
                    ${pageIndex}
                </button>`;
            }
        } else {
            for (let pageIndex = activeIndex - 3; pageIndex < activeIndex + 4; pageIndex++) {
                pagination.innerHTML += `
               <button class="page-btn ${activeIndex === pageIndex ? 'active-btn' : 'null'}" data-index="${pageIndex}">
                    ${pageIndex}
                </button>`;
            }
        }
    }

    pagination.innerHTML += `             
                <i class="fas fa-angle-right"></i>    
                <i class="fas fa-angle-double-right"></i>                           
            `;
}

/****************************************
 * displayBooksOnPage() - helper method
 ****************************************/

export async function getNumberOfBooks(url) {
    fetch(url)
        .then((res) => res.json())
        .then((data) => {
            sessionStorage.setItem('numberOfRecord', data);
        });
}

// Example of cascaded drop doen list
// https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_cascading_dropdown
