import { users, rolesTemp } from './utils.js';

// ***********************
// start Input targeting
// ***********************
const userId = document.getElementById('userId');
const userPid = document.getElementById('userPid');
const userName = document.getElementById('userName');
const userEmail = document.getElementById('userEmail');
const allUsers = document.getElementById('allUsers');

// create (POST)
const createUserPid = document.getElementById('createUserPid');
const createUserName = document.getElementById('createUserName');
const createUserEmail = document.getElementById('createUserEmail');
const createUserPassword = document.getElementById('createUserPassword');

// update (PUT)
const updateRoleName = document.getElementById('updateRoleName');
const updatePid = document.getElementById('updatePid');

// delete role
const deleteRolePid = document.getElementById('deleteRolePid');
const deleteRoleName = document.getElementById('deleteRoleName');

// delet user
const inputRemoveUser = document.getElementById('inputRemoveUser');

// ***********************
// end Input targeting
// ***********************

// ***********************
// Initial value Set
// ***********************
userId.value = 3;
userPid.value = 2222;
userName.value = `shabtay shalem`;
userEmail.value = `odel.shalem@gmail.com`;
allUsers.value = `NA`;

createUserPid.value = 5555;
createUserName.value = 'temp';
createUserEmail.value = 'temp@gmail.com';
createUserPassword.value = 102030;

updateRoleName.value = 'SUPER-TEMP';
updatePid.value = 5555;

deleteRoleName.value = 'ADMIN';
deleteRolePid.value = 1111;

inputRemoveUser.value = 5555;

// ***********************
// Button's targeting
// ***********************
const getUserById = document.getElementById('getUserById');
const getUserByPid = document.getElementById('getUserByPid');
const getUserByName = document.getElementById('getUserByName');
const getUserByEmail = document.getElementById('getUserByEmail');
const getAllUsers = document.getElementById('getAllUsers');

const createUsers = document.getElementById('createUsers');
const updateUserWithRoles = document.getElementById('updateUserWithRoles');
const removeRole = document.getElementById('removeRole');
const removeUser = document.getElementById('removeUser');

// ***********************
// Url's
// ***********************
const GET_USER_BY_ID_URL = `http://localhost:8080/users/getUserById`;
const GET_USER_BY_PID_URL = `http://localhost:8080/users/getUserByPid`;
const GET_USER_BY_NAME_URL = `http://localhost:8080/users/getUserByName`;
const GET_USER_BY_EMAIL_URL = `http://localhost:8080/users/getUserEmail`;
const GET_ALL_USERS_URL = `http://localhost:8080/users/allUsers`;

const CREATE_URL = `http://localhost:8080/users/create`;
const ADD_ROLE_URL = `http://localhost:8080/users/addRole`;
const REMOVE_ROLE_URL = `http://localhost:8080/users/removeRole`;
const REMOVE_USER_URL = `http://localhost:8080/users/removeUser`;

// ****************************
//          Get
// ****************************
getUserById.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_USER_BY_ID_URL}/${userId.value}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

getUserByPid.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_USER_BY_PID_URL}/${userPid.value}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

getUserByName.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_USER_BY_NAME_URL}/${userName.value}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

getUserByEmail.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_USER_BY_EMAIL_URL}/${userEmail.value}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

getAllUsers.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_ALL_USERS_URL}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

// ****************************
//          Post
// ****************************
createUsers.addEventListener('click', () => {
  const user = {
    pid: createUserPid.value,
    name: createUserName.value,
    email: createUserEmail.value,
    password: createUserPassword.value,
  };
  executeFetch(CREATE_URL, user, 'post');
});

// ******************************
//          Delete
// ******************************
removeRole.addEventListener('click', () => {
  executeFetch(`${REMOVE_ROLE_URL}/${deleteRolePid.value}/${deleteRoleName.value}`, '', 'delete');
});

removeUser.addEventListener('click', () => {
  executeFetch(`${REMOVE_USER_URL}/${inputRemoveUser.value}`, '', 'delete');
});

// ******************************
//          PUT
// ******************************
updateUserWithRoles.addEventListener('click', () => {
  const role = {
    role: updateRoleName.value,
  };
  const url = `${ADD_ROLE_URL}/${updatePid.value}`;
  executeFetch(url, role, 'put');
});

// *********************************
//        Fetch API
// *********************************
function executeFetch(url, data, requestMethod) {
  const options = {
    method: requestMethod,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  };

  fetch(url, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
}
