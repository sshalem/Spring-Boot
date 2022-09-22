const getAllUsers = document.querySelector('#getAllUsers');
const getUserByName = document.querySelector('#getUserByName');
const getUserById = document.querySelector('#getUserById');

const createUser = document.querySelector('#createUser');
const createBtn = document.querySelector('.createBtn');

const result = document.getElementById('result');
const tbody = document.getElementById('tbody');

const toggleErrorUserId = document.getElementById('toggleErrorUserId');
const toggleErrorUsername = document.getElementById('toggleErrorUsername');

const errorMessageFromServer = document.querySelector('.error-message');

const API_URL = 'http://localhost:8080/api';
const API_URL_GET_ALL = `http://localhost:8080/api/users/getAllUsers`;
const API_GET_BY_ID = `http://localhost:8080/api/users/get/userId`;
const API_GET_BY_USERNAME = `http://localhost:8080/api/users/get`;

const API_URL_DELETE = `http://localhost:8080/api/app/superadmin/delete`;
const API_CREATE_USER = `http://localhost:8080/api/app/superadmin/create`;

let csrf = '';

/***************
 get all Users
 **************/
getAllUsers.addEventListener('click', function () {
    executeGetMethod(`${API_URL_GET_ALL}`);
});

/*********************
 get user by username
 *********************/
getUserByName.addEventListener('click', function () {
    let username = document.querySelector('#username').value;
    if (username === '') {
        tbody.innerHTML = ``;
        toggleErrorUsername.classList.toggle('hidden_username');
        setTimeout(() => {
            toggleErrorUsername.classList.toggle('hidden_username');
        }, 2000);
    } else {
        executeGetMethod(`${API_GET_BY_USERNAME}/${username}_requested`);
    }
});

/*************************
	get user by user id
 *************************/
getUserById.addEventListener('click', function () {
    const userId = document.querySelector('#userId').value;
    if (userId === '') {
        tbody.innerHTML = ``;
        toggleErrorUserId.classList.toggle('hidden_id');
        setTimeout(() => {
            toggleErrorUserId.classList.toggle('hidden_id');
        }, 2000);
    } else {
        executeGetMethod(`${API_GET_BY_ID}/${userId}`);
    }
});

/*********************
	create User
 *********************/

// Create User
createUser.addEventListener('click', function () {
    document.getElementById('toggle-inputs').classList.toggle('visible');
    document.getElementById('userName').value = '';
    document.getElementById('password').value = '';
    document.getElementById('email').value = '';
});

createBtn.addEventListener('click', function () {
    const url = `${API_CREATE_USER}`;

    const username = document.getElementById('userName').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
    const roleName = document.getElementById('roleName');

    const role = roleName.options[roleName.options.selectedIndex].value;
    const newUser = new User(username, password, email, role);

    executePostPutDelete(url, 'post', newUser);
    document.getElementById('toggle-inputs').classList.toggle('visible');
});

class User {
    constructor(userName, password, email, role) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}

/******************************
 *        Help Methods
 ******************************/

function executeGetMethod(url) {
    const options = {
        credentials: 'include',
    };

    fetch(url, options)
        .then((res) => {
            for (let header of res.headers.entries()) {
                if (header[0] === 'x-csrf-token') {
                    console.log(header);
                    csrf = header[1];
                }
            }
            return res.json();
        })
        .then((data) => {
            if (data.status == 500 || data.status == 403) {
                console.log(data);
                logTraceErrorMessageFromServer(data);
            } else {
                if (Array.isArray(data)) {
                    tbody.innerHTML = ``;
                    errorMessageFromServer.innerHTML = '';
                    extractDataAndDisplayUsers(data);
                } else {
                    console.log(data);
                    tbody.innerHTML = ``;
                    errorMessageFromServer.innerHTML = '';
                    let roles = extractDataAndReturnRoles(data);
                    displayDataOnPage(tbody, data, roles);
                }
            }
            const deleteUsers = document.querySelectorAll('.delete');
            // console.log(deleteUsers);

            deleteUsers.forEach((user) => {
                user.addEventListener('click', () => {
                    const url = `${API_URL_DELETE}/${user.dataset.userId}`;
                    executePostPutDelete(url, 'delete', null);
                });
            });
        });
}

function executePostPutDelete(url, request, user) {
    const options = {
        credentials: 'include',
        method: `${request}`,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': `${csrf}`,
        },
        body: JSON.stringify(user),
    };

    fetch(url, options)
        .then((res) => res.json())
        .then((data) => {
            if (data.status == 500 || data.status == 403) {
                console.log(data);
                logTraceErrorMessageFromServer(data);
            } else {
                console.log(data);
                executeGetMethod(API_URL_GET_ALL);
            }
        });
}

function displayDataOnPage(tbody, data, roles) {
    tbody.innerHTML += `
            <tr class="table__users-tbody-tr">
                <td class="table__users-tbody-td">${data.id}</td>
                <td class="table__users-tbody-td">${data.username}</td>
                <td class="table__users-tbody-td">${data.email}</td>
                <td class="table__users-tbody-td">${roles}</td>
                <td class="table__users-tbody-td">                    
                    <button id="update-${data.username}" class="btn update">update</button>
                    <button data-user-id=${data.id} class="btn delete">delete</button>
                </td>
            </tr>                        
            `;
}

function extractDataAndReturnRoles(data) {
    let currentRole = ``;
    let roles = ``;
    if (Array.isArray(data.roles)) {
        // console.log('data.roles is an array');
        data.roles.forEach((rl) => {
            roles = currentRole.concat(`${rl.role}`);
            currentRole = roles;
            currentRole += ` , `;
        });
    } else {
        roles = `${data.role}`;
    }
    return roles;
}

function extractDataAndDisplayUsers(data) {
    data.forEach((user) => {
        let currentRole = ``;
        let roles = ``;
        user.roles.forEach((rl) => {
            roles = currentRole.concat(`${rl.role}`);
            currentRole = roles;
            currentRole += ` , `;
        });

        displayDataOnPage(tbody, user, roles);
    });
}

function logTraceErrorMessageFromServer(data) {
    tbody.innerHTML = ``;
    errorMessageFromServer.innerHTML = '';

    for (let key in data) {
        if (key === 'trace') {
            let text = data[key];
            let indexEception = text.indexOf('Exception');
            console.log(`${key} : ${text.substr(0, indexEception + 9)}`);
            continue;
        }
        console.log(`${key} : ${data[key]}`);

        if (key === 'status') {
            errorMessageFromServer.innerHTML += `
                <div class="error-message-div">
                    <div class="em-title">${key}:</div>
                    <div class="em-title-description" id="em-status">${data[key]}</div>
                </div>
                `;
        } else {
            errorMessageFromServer.innerHTML += `
                <div class="error-message-div">
                    <div class="em-title">${key}:</div>
                    <div class="em-title-description">"${data[key]}"</div>
                </div>            
                `;
        }
    }
}
