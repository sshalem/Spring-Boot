export function homeContent(content) {
    content.innerHTML = `
    <div style="width:50vw">
        welcome to <span class="aside__text">users home Page</span>, 
        please choose one of the options from the nav on the side
    </div>
    `;
}

export function createContent(content) {
    content.innerHTML = `
    <div>
        <p>Type User name</p>
        <p><input style="height: 2.5rem; width: 19rem;" type="text" name="create"/></p>
        <p><button>create</button></p>
    </div>`;
}

export function allUsers(content, e) {
    content.innerHTML = `
    <h6 style="margin-bottom:1rem;text-decoration:underline;font-style:italic">get all users</h6>
    <table style="font-size:1rem">
        <thead>
            <tr>
                <th>id</th>
                <th>firstname</th>
                <th>lastname</th>
                <th>email</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>`;

    // this is to access the <ul> element
    // console.log(e.path);
    // console.log(e.path[3].children[1].children[0].children[0].children[1]);
    const trElement = e.path[3].children[1].children[0].children[1].children[1];

    fetch('http://localhost:8080/users/all')
        .then((res) => res.json())
        .then((users) => {
            users.forEach((user) => {
                const { id, firstname, lastname, email } = user;
                trElement.innerHTML += `
                <tr class="tr-${id}">
                    <td>${id}</td>
                    <td>${firstname}</td>
                    <td>${lastname}</td>
                    <td>${email}</td>
                    <td><button class="btn-edit btn-edit-${id}">edit</button></td>
                    <td><button class="btn-delete btn-delete-${id}">delete</button></td>
                </tr>
                <tr class="hide hide-${id}">
                    <td><input type="text" name="id" id="id-${id}" class="input-css"/></td>
                    <td><input type="text" name="firstname" id="firstname-${id}" class="input-css"/></td>
                    <td><input type="text" name="lastname" id="lastname-${id}" class="input-css"/></td>
                    <td><input type="text" name="email" id="email-${id}" class="input-css"/></td>                    
                    <td><button class="btn-update btn-update-${id}">update</button></td>
                    <td><button class="btn-cancel btn-cancel-${id}">cancel</button></td>
                </tr>`;
            });

            users.forEach((user) => {
                const { id, firstname, lastname, email } = user;
                document.getElementById(`id-${id}`).value = id;
                document.getElementById(`firstname-${id}`).value = firstname;
                document.getElementById(`lastname-${id}`).value = lastname;
                document.getElementById(`email-${id}`).value = email;

                let editBtn = document.querySelector(`.btn-edit-${id}`);
                let deleteBtn = document.querySelector(`.btn-delete-${id}`);
                let updateBtn = document.querySelector(`.btn-update-${id}`);
                let cancelBtn = document.querySelector(`.btn-cancel-${id}`);

                editBtn.addEventListener('click', function () {
                    document.querySelector(`.hide-${id}`).classList.remove('hide');
                    document.querySelector(`.tr-${id}`).classList.add('hide');
                    console.log(trElement.innerHTML);
                });

                cancelBtn.addEventListener('click', function () {
                    document.querySelector(`.hide-${id}`).classList.add('hide');
                    document.querySelector(`.tr-${id}`).classList.remove('hide');
                });

                deleteBtn.addEventListener('click', function () {
                    console.log('ele');
                    const options = {
                        method: 'delete',
                    };
                    fetch(`http://localhost:8080/users/${id}`, options)
                        .then((res) => res.text())
                        .then((data) => console.log(data));
                });
            });
        });
}

export function userById(content) {
    content.innerHTML = `
    <div>
        <p>get User By Id</p>
        <p><input id="getUserId" style="height: 2.5rem; width: 19rem;" type="text" name="create"/></p>
        <p><button id="btnGetUser">get user</button></p>
    </div>`;

    document.getElementById('btnGetUser').addEventListener('click', function () {
        const userId = document.getElementById('getUserId').value;
        fetch(`http://localhost:8080/users/id/${userId}`)
            .then((res) => res.json())
            .then((data) => console.log(data));
    });
}

export function userByEmail(content) {
    content.innerHTML = `
    <div>
        <p>get User By Email</p>
        <p><input id="getUserEmail" style="height: 2.5rem; width: 19rem;" type="text" name="create"/></p>
        <p><button id="btnGetUser">get user</button></p>
    </div>`;

    document.getElementById('btnGetUser').addEventListener('click', function () {
        const userEmail = document.getElementById('getUserEmail').value;
        fetch(`http://localhost:8080/users/email/${userEmail}`)
            .then((res) => res.json())
            .then((data) => console.log(data));
    });
}

export function userByFirstname(content) {
    content.innerHTML = `
    <div>
        <p>get User By First name</p>
        <p><input id="getUserFirstname" style="height: 2.5rem; width: 19rem;" type="text" name="create"/></p>
        <p><button id="btnGetUser">get user</button></p>
    </div>`;

    document.getElementById('btnGetUser').addEventListener('click', function () {
        const userFirstname = document.getElementById('getUserFirstname').value;
        fetch(`http://localhost:8080/users/firstname/${userFirstname}`)
            .then((res) => res.json())
            .then((data) => console.log(data));
    });
}
