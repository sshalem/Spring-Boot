import { users, rolesAriel, rolesItamar } from './utils.js';

// Input targeting
const userId = document.getElementById('userId');
const userPid = document.getElementById('userPid');
const userName = document.getElementById('userName');
const userEmail = document.getElementById('userEmail');
const allUsers = document.getElementById('allUsers');
// End Input targeting

// Initial value Set
userId.value = 3;
userPid.value = 2222;
userName.value = `shabtay shalem`;
userEmail.value = `odel.shalem@gmail.com`;
allUsers.value = `NA`;

const getUserById = document.getElementById('getUserById');
const getUserByPid = document.getElementById('getUserByPid');
const getUserByName = document.getElementById('getUserByName');
const getUserByEmail = document.getElementById('getUserByEmail');
const getAllUsers = document.getElementById('getAllUsers');

const dataDisplay = document.querySelector('.data');

const createUsers = document.getElementById('createUsers');
const updateUserWithRoles = document.getElementById('updateUserWithRoles');
const removeRole = document.getElementById('removeRole');

const GET_USER_BY_ID_URL = `http://localhost:8080/users/getUserById`;
const GET_USER_BY_PID_URL = `http://localhost:8080/users/getUserByPid`;
const GET_USER_BY_NAME_URL = `http://localhost:8080/users/getUserByName`;
const GET_USER_BY_EMAIL_URL = `http://localhost:8080/users/getUserEmail`;
const GET_ALL_USERS_URL = `http://localhost:8080/users/allUsers`;

const CREATE_URL = `http://localhost:8080/users/create`;
const ADD_ROLE_URL = `http://localhost:8080/users/addRole`;
const REMOVE_ROLE_URL = `http://localhost:8080/users/removeRole`;

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
    .then((data) => {
      console.log(data);
      const { id, pid, email, name, password } = data;
      dataDisplay.innerHTML = `{id:${id}, pid:${pid}, name:"${name}", email:"${email}", password:"${password}}`;
    })
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

createUsers.addEventListener('click', () => {
  users.forEach((user) => {
    executeFetch(CREATE_URL, user, 'post');
  });
});

removeRole.addEventListener('click', () => {
  const roleEntity = {
    role: 'ADMIN',
  };

  executeFetch(`${REMOVE_ROLE_URL}/1111`, roleEntity, 'delete');
});

updateUserWithRoles.addEventListener('click', () => {
  users.forEach((user) => {
    if (user.name === 'ariel shalem') {
      rolesAriel.forEach((role) => {
        const url = `${ADD_ROLE_URL}/5555`;
        executeFetch(url, role, 'put');
      });
    }
    if (user.name === 'itamar shalem') {
      rolesItamar.forEach((role) => {
        const url = `${ADD_ROLE_URL}/7777`;
        executeFetch(url, role, 'put');
      });
    }
  });
});

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
    .then((data) => {
      if (requestMethod === `delete`) console.log(data);
      if (requestMethod === `get`) console.log(data);
      else console.log('execute');
    })
    .catch((error) => console.log('error', error));
}
