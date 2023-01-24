const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');

const register = document.getElementById('register');
const validation = document.getElementById('validation');
let userValidate = false;

register.addEventListener('click', () => {
  if (username.value === '' || email.value === '' || password.value === '') {
    validation.classList.add('alert-danger');
    validation.textContent = `one of the fields is missing`;
    setTimeout(() => {
      validation.classList.remove('alert-danger');
      validation.textContent = '';
    }, 2000);
  } else {
    const userDetails = {
      username: username.value,
      email: email.value,
      password: password.value,
    };
    const registerUrl = `http://localhost:8080/auth/register`;
    userRegister(registerUrl, userDetails);
  }
});

function userRegister(registerUrl, userDetails) {
  console.log(userDetails);
  fetch(registerUrl, {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(userDetails),
  })
    .then((data) => data.json())
    .then((res) => {
      if (res.username === username.value) {
        cleanInputFields();
        validation.classList.add('alert-success');
        validation.textContent = `Registered Successfully`;
        setTimeout(() => {
          validation.classList.remove('alert-success');
          validation.textContent = '';
        }, 2000);
      }
    });
}

function cleanInputFields() {
  username.value = '';
  email.value = '';
  password.value = '';
}
