const csrfId = document.getElementById('csrfId');
let csrf = null;

/**********************************************
 * Once Page loads , I check Cookies
 * and looking for the X-XSRF-TOKEN cookie ,
 * to add it to the hidden input
 **********************************************/

let ck = document.cookie;
let splitCookie = ck.split('=');
console.log(ck);
console.log(splitCookie);
const csrfAdd = document.getElementById('csrfAdd');
window.addEventListener('load', () => {
    // csrfAdd.innerHTML = `<input type="hidden" name="_csrf" value="${splitCookie[1]}">`;
    csrfId.setAttribute('value', `${splitCookie[1]}`);
});

/***********************************
 * Validation for user credentials
 ***********************************/
if (window.location.search != '' && window.location.search != '?') {
    document.getElementById('err').innerHTML = `
    		<div class="alert alert-danger">username / password incorrect</div>
    		`;
}
