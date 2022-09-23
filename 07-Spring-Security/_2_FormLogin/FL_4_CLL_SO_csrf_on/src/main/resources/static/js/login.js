const csrfId = document.getElementById('csrfId');
let csrf = null;

/**********************************************
 * Once Page loads , I check the headers
 * and looking for the X-CSRF-TOKEN header ,
 * to add it to the hidden input
 **********************************************/
window.addEventListener('load', () => {
    fetch(location)
        .then((res) => {
            for (let header of res.headers.entries()) {
                if (header[0] === 'x-csrf-token') {
                    csrfId.setAttribute('value', `${header[1]}`);
                    csrf = header[1];
                    console.log(csrfId);
                }
                console.log(header);
            }
        })
        .then();
});

/***********************************
 * Validation for user credentials
 ***********************************/
if (window.location.search != '' && window.location.search != '?') {
    document.getElementById('err').innerHTML = `
    		<div class="alert alert-danger">username / password incorrect</div>
    		`;
}
