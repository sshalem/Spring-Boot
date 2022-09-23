import { homeContent, createContent, allUsers, userById, userByEmail, userByFirstname } from './JS/data.js';

const home = document.getElementById('home');
const creatUser = document.getElementById('btnCreateUser');
const getUserById = document.getElementById('btnGetUserById');
const getUserByEmail = document.getElementById('btnGetUserByEmail');
const getUserByFirstname = document.getElementById('btnGetUserByFirstname');
const getUsersByPostDetailsContent = document.getElementById('btnGetUsersByPostDetailsContent');
const getUserWhereFirstnameContains = document.getElementById('btnGetUserWhereFirstnameContains');
const getAllUsers = document.getElementById('btnAllUsers');
const getPostByUserId = document.getElementById('btnGetPostByUserId');
const getPostByUserFirstname = document.getElementById('btnGetPostByUserFirstname');
const getPostByUserEmail = document.getElementById('btnGetPostByUserEmail');

const aside = document.querySelector('.aside');
const asideContent = document.querySelector('.aside__content');

home.addEventListener('click', function () {
    homeContent(asideContent);
    aside.classList.remove('aside-addition');
});

creatUser.addEventListener('click', function () {
    createContent(asideContent);
    aside.classList.add('aside-addition');
});

getAllUsers.addEventListener('click', function (e) {
    allUsers(asideContent, e);
    aside.classList.add('aside-addition');
});

getUserById.addEventListener('click', function () {
    userById(asideContent);
    aside.classList.add('aside-addition');
});

getUserByEmail.addEventListener('click', function () {
    userByEmail(asideContent);
    aside.classList.add('aside-addition');
});

getUserByFirstname.addEventListener('click', function () {
    userByFirstname(asideContent);
    aside.classList.add('aside-addition');
});
getUsersByPostDetailsContent.addEventListener('click', function () {});
getUserWhereFirstnameContains.addEventListener('click', function () {});

getPostByUserId.addEventListener('click', function () {});
getPostByUserFirstname.addEventListener('click', function () {});
getPostByUserEmail.addEventListener('click', function () {});

// updateUser.addEventListener('click', function () {});
// deleteUser.addEventListener('click', function () {});
