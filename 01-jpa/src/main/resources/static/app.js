import { users, rolesAriel, rolesKarin, rolesShabtay, rolesOdel } from './utils.js';

const createUsers = document.getElementById('createUsers');
const updateUserWithRoles = document.getElementById('updateUserWithRoles');
const removeRole = document.getElementById('removeRole');

const baseUrl = `http://localhost:8080`;
const CREATE_URL = `http://localhost:8080/create`;
const ADD_ROLE_URL = `http://localhost:8080/addRole`;
const REMOVE_ROLE_URL = `http://localhost:8080/removeRole`;

createUsers.addEventListener('click', () => {
  users.forEach((user) => {
    executeFetch(CREATE_URL, user, 'post');
  });
});

removeRole.addEventListener('click', () => {
  const roleEntity = {
    role: 'ADMIN',
  };

  executeFetch(`${REMOVE_ROLE_URL}/1111`, roleEntity, 'post');
});

updateUserWithRoles.addEventListener('click', () => {
  users.forEach((user) => {
    if (user.name === 'shabtay shalem') {
      rolesShabtay.forEach((role) => {
        const url = `${ADD_ROLE_URL}/1111`;
        executeFetch(url, role, 'post');
      });
    }
    if (user.name === 'karin shalem') {
      rolesKarin.forEach((role) => {
        const url = `${ADD_ROLE_URL}/2222`;
        executeFetch(url, role, 'post');
      });
    }
    if (user.name === 'ariel shalem') {
      rolesAriel.forEach((role) => {
        const url = `${ADD_ROLE_URL}/3333`;
        executeFetch(url, role, 'post');
      });
    }
    if (user.name === 'odel shalem') {
      rolesOdel.forEach((role) => {
        const url = `${ADD_ROLE_URL}/4444`;
        executeFetch(url, role, 'post');
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
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
}
