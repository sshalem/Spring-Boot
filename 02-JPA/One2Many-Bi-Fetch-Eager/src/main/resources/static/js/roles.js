const getRoleById = document.getElementById('getRoleById');
const getUsersWithRolename = document.getElementById('getUsersWithRolename');
const getRoleByPid = document.getElementById('getRoleByPid');
const getAllRoles = document.getElementById('getAllRoles');

const GET_ROLES_BY_ID_URL = `http://localhost:8080/roles/getRolesById`;
const GET_USERS_WITH_ROLE_NAME_URL = `http://localhost:8080/roles/getUsersWithRoleName`;
const GET_ROLES_BY_PID_URL = `http://localhost:8080/roles/getRolesByPid`;
const GET_ALL_ROLES_URL = `http://localhost:8080/roles/allRoles`;

// ******************
// ****   Roles *****
// ******************

// Input targeting
const roleId = document.getElementById('roleId');
const roleRolename = document.getElementById('roleRolename');
const rolePid = document.getElementById('rolePid');
const allRoles = document.getElementById('allRoles');
// End Input targeting

// Initial value Set
roleId.value = 3;
rolePid.value = 2222;
roleRolename.value = `CEO`;
allRoles.value = `NA`;

// ********************
// **** End Roles  ****
// ********************

getRoleById.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_ROLES_BY_ID_URL}/${roleId.value}`, options)
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
    })
    .catch((error) => console.log('error', error));
});

getUsersWithRolename.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_USERS_WITH_ROLE_NAME_URL}/${roleRolename.value}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

getRoleByPid.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_ROLES_BY_PID_URL}/${rolePid.value}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
});

getAllRoles.addEventListener('click', () => {
  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  fetch(`${GET_ALL_ROLES_URL}`, options)
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((error) => console.log('error', error));
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
