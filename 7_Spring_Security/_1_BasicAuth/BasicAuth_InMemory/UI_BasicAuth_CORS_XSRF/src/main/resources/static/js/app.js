const url_1 = document.querySelector('#v1-1');
const url_2 = document.querySelector('#v1-2');
const url_3 = document.querySelector('#v1-3');
const test = document.querySelector('#test');
const sendData = document.getElementById('sendData');
const updateData = document.getElementById('updateData');
const deleteData = document.getElementById('deleteData');
const result = document.getElementById('result');
const dataFromServer = document.getElementById('dataFromServer');

let xsrf = null;

url_1.addEventListener('click', function () {
	initiateFetchGet('http://localhost:8080/api/v1/1');
});

url_2.addEventListener('click', function () {
	initiateFetchGet('http://localhost:8080/api/v1/2');
});

url_3.addEventListener('click', function () {
	initiateFetchGet('http://localhost:8080/api/v1/3');
});



test.addEventListener('click', function () {
	const options = {
		credentials: 'include',
	};

	const url = 'http://localhost:8080/api/app/test';
	fetch(url, options)
		.then((res) => {
			xsrf = document.cookie.split("=")[1];
			console.log(xsrf);
			
			return res.text();
		})
		.then((data) => {
			console.log(data);
			result.innerHTML = `${data}`;
		});
});


sendData.addEventListener('click', function () {

	const url = `http://localhost:8080/api/post`;

	const studentName = document.getElementById('studentName').value;
	const studentId = document.getElementById('studentId').value;

	const student = new Student(studentId, studentName);
	initiatePostPutDelete(url, 'post', student);
});

updateData.addEventListener('click', function () {

	const url = `http://localhost:8080/api/put`;

	const updateName = document.getElementById('updateName').value;
	const updateId = document.getElementById('updateId').value;

	const student = new Student(updateId, updateName);
	initiatePostPutDelete(url, 'put', student);
});

deleteData.addEventListener('click', function () {

	const deleteName = document.getElementById('deleteName').value;
	const deleteId = document.getElementById('deleteId').value;

	const url = `http://localhost:8080/api/delete/${deleteId}`;

	const student = new Student(deleteId, deleteName);
	initiatePostPutDelete(url, 'delete', student);
});

class Student {
	constructor(studentId, studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	};
}

function initiateFetchGet(url) {
	const options = {
		credentials: 'include',
	};
	fetch(url, options)
		.then((res) => {
			xsrf = document.cookie.split("=")[1];
			console.log(xsrf);	
			return res.json();
		})
		.then((data) => {
			console.log(data);
			result.innerText = `${data.studentId}, ${data.studentName}`;
		});
}

function initiatePostPutDelete(url, request, student) {

	const options = {
		credentials: 'include',
		method: `${request}`,
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json",
			"X-XSRF-TOKEN": `${xsrf}`
		},
		body: JSON.stringify(student)
	}
	fetch(url, options)
		.then(res => res.text())
		.then(data => {
			console.log(data);
			dataFromServer.innerText = data;
		});
}
