import User from './User.js';

const btnGetById = document.querySelector('.btnGetById');
const btnGetByUsername = document.querySelector('.btnGetByUsername');
const createBtn = document.querySelector('.createBtn');
const createUser = document.querySelector('.postBtn');
const btnGetAll = document.querySelector('.btnGetAll');
const getAllUserFromServer = document.getElementById('getAllUserFromServer');
// const updateUser = document.querySelector('.putBtn');

const API_URL = 'http://localhost:8080/api';
const API_URL_GET_ALL = `http://localhost:8080/api/users/getAllUsers`;
const API_URL_DELETE = `http://localhost:8080/api/app/superadmin/delete`;
const API_GET_BY_ID = `http://localhost:8080/api/users/get/userId`;
const API_GET_BY_USERNAME = `http://localhost:8080/api/users/get`;
const API_CREATE_USER = `http://localhost:8080/api/app/superadmin/create`;

fetch(location).then((res) => {
    console.log("i'm in fetch(location) method");
    for (let header of res.headers.entries()) {
        // all headers return as small lettes from server
        // console.log(header);
        if (header[0] === 'userid') {
            let userId = header[1];
            fetch(`${API_GET_BY_ID}/${userId}`)
                .then((res) => res.json())
                .then((data) => {
                    console.log(data);
                    const user = document.getElementById('user-logged');
                    user.innerHTML = data.username;
                });
        }
    }
});

// GetById
btnGetById.addEventListener('click', function () {
    let userId = document.getElementById('inputGetById').value;
    console.log(userId);
    if (userId === '') {
        userId = 0;
    }
    const url = `${API_GET_BY_ID}/${userId}`;
    executGet(url);
});

// GetByUsername
btnGetByUsername.addEventListener('click', function () {
    const username = document.getElementById('inputGetByUsername').value;
    const url = `${API_GET_BY_USERNAME}/${username}_requested`;
    executGet(url);
});

// Get ALL USERS
btnGetAll.addEventListener('click', function () {
    executGetAll(API_URL_GET_ALL);
});

// Create User
createBtn.addEventListener('click', function () {
    document.getElementById('toggle-inputs').classList.toggle('visible');
    document.getElementById('inputCreateUsername').value = '';
    document.getElementById('inputCreatePassword').value = '';
    document.getElementById('inputCreateEmail').value = '';
});

createUser.addEventListener('click', function () {
    const username = document.getElementById('inputCreateUsername').value;
    const password = document.getElementById('inputCreatePassword').value;
    const email = document.getElementById('inputCreateEmail').value;

    const user = new User(username, password, email);
    executePostPutDelete(API_CREATE_USER, 'post', user);
    document.getElementById('toggle-inputs').classList.toggle('visible');
});

// Update User
// updateUser.addEventListener('click', function () {
//     updateUser.dataset.userId;

// const username = document.getElementById('inputUpdateUsername').value;
// const password = document.getElementById('inputUpdatePassword').value;
// const email = document.getElementById('inputUpdateEmail').value;

// const url = `${API_URL}/users/update`;

// const user = new User(username, password, email);
// executePostPutDelete(url, 'put', user);
// });

function executGetAll(url) {
    const options = {
        credentials: 'include',
    };
    fetch(url, options)
        .then((res) => {
            return res.json();
        })
        .then((data) => {
            if (url == `${API_URL}/users/getAllUsers`) {
                getAllUserFromServer.innerHTML = ` `;
                // console.log(data);
                data.forEach((element) => {
                    getAllUserFromServer.innerHTML += `<tr>
                                <td>${element.userId}</td>
                                <td>${element.username}</td>
                                <td>${element.email}</td>
                                <td><button id="update-${element.username}" class="btn putBtn">update</button></td>
                                <td><button class="btn deleteBtn" data-user-id=${element.userId}>delete</button></td>
                            </tr>`;
                });
            }
            const deleteUsers = document.querySelectorAll('.deleteBtn');
            // console.log(deleteUsers);
            deleteUsers.forEach((user) => {
                user.addEventListener('click', () => {
                    const url = `${API_URL_DELETE}/${user.dataset.userId}`;
                    executePostPutDelete(url, 'delete', null);
                    executGetAll(API_URL_GET_ALL);
                });
            });
        });
}

function executGet(url) {
    const options = {
        credentials: 'include',
    };
    fetch(url, options)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            if (data.status === 500) {
                const articleError = document.getElementById('errorGetById');
                const errorMsg = document.querySelector('.errorMessage');
                errorMsg.innerHTML = `${data.message}`;
                articleError.classList.remove('visible');
                setTimeout(() => {
                    // errorMsg.innerHTML = `${data.message}`;
                    articleError.classList.toggle('visible');
                }, 3000);
            } else if (url.includes(API_GET_BY_ID) || url.includes(API_GET_BY_USERNAME)) {
                getAllUserFromServer.innerHTML = ` `;
                // console.log(data);
                getAllUserFromServer.innerHTML += `<tr>
                                <td>${data.userId}</td>
                                <td>${data.username}</td>
                                <td>${data.email}</td>
                                <td><button id="update-${data.username}" class="btn putBtn">update</button></td>
                                <td><button class="btn deleteBtn" data-user-id=${data.userId}>delete</button></td>
                            </tr>`;
            }
            const deleteUser = document.querySelector('.deleteBtn');
            deleteUser.addEventListener('click', () => {
                // console.log(deleteUser.dataset);
                const url = `${API_URL_DELETE}/${deleteUser.dataset.userId}`;
                executePostPutDelete(url, 'delete', null);
                executGetAll(API_URL_GET_ALL);
            });
        });
}

function executePostPutDelete(url, request, user) {
    // console.log(url);
    const options = {
        credentials: 'include',
        method: `${request}`,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    };
    fetch(url, options)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            executGetAll(API_URL_GET_ALL);
        });
}
