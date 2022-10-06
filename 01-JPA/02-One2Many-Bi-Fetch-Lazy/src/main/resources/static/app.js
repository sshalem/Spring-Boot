import { users, rolesAriel, rolesKarin, rolesShabtay, rolesOdel } from './utils.js';

const getUser = document.getElementById('getUser');
const createUsers = document.getElementById('createUsers');
const updateUserWithRoles = document.getElementById('updateUserWithRoles');
const removeRole = document.getElementById('removeRole');

const GET_USER_URL = `http://localhost:8080/getUser`;
const CREATE_URL = `http://localhost:8080/create`;
const ADD_ROLE_URL = `http://localhost:8080/addRole`;
const REMOVE_ROLE_URL = `http://localhost:8080/removeRole`;

getUser.addEventListener('click', () => {
  const username = 'shabtay shalem';

  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_USER_URL}/${username}`, options)
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
    if (user.name === 'shabtay shalem') {
      rolesShabtay.forEach((role) => {
        const url = `${ADD_ROLE_URL}/1111`;
        executeFetch(url, role, 'put');
      });
    }
    if (user.name === 'karin shalem') {
      rolesKarin.forEach((role) => {
        const url = `${ADD_ROLE_URL}/2222`;
        executeFetch(url, role, 'put');
      });
    }
    if (user.name === 'ariel shalem') {
      rolesAriel.forEach((role) => {
        const url = `${ADD_ROLE_URL}/3333`;
        executeFetch(url, role, 'put');
      });
    }
    if (user.name === 'odel shalem') {
      rolesOdel.forEach((role) => {
        const url = `${ADD_ROLE_URL}/4444`;
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
