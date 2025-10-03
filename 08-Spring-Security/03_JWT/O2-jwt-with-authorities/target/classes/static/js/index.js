const email = document.getElementById('email');
const password = document.getElementById('password');

const login = document.getElementById('login');
const validation = document.getElementById('validation');

login.addEventListener('click', () => {
  if (email.value === '' || password.value === '') {
    validation.classList.add('alert-danger');
    validation.textContent = `one of the fields is missing`;
    setTimeout(() => {
      validation.classList.remove('alert-danger');
      validation.textContent = '';
    }, 2000);
  } else {
    const userDetails = {
      email: email.value,
      password: password.value,
    };
    const loginUrl = `http://localhost:8080/auth/login`;
    userLogin(loginUrl, userDetails);
  }
});

function userLogin(loginUrl, userDetails) {
  fetch(loginUrl, {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(userDetails),
  })
    .then((data) => data.json())
    .then((res) => {
      sessionStorage.setItem('token', res.jwtToken);
      if (res.jwtToken) {
        window.location.replace('landing.html');
      }
    });
}

if (window.location.search != '' && window.location.search != '?') {
  document.getElementById('err').innerHTML = `
    		<div class="alert alert-danger">username / password incorrect</div>
    		`;
}
