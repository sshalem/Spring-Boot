const connectSection = document.getElementById('connect-section');
const connectBtn = document.getElementById('connect-btn');
const disconnect = document.getElementById('disconnect');
const sendMessage = document.getElementById('sendMessage');
const conversation = document.getElementById('conversation');
const usernameInput = document.getElementById('username-input');
const displayMessages = document.getElementById('messages');

// Globaly define this variable
let stompClient = null;

/*******************
 * Event Listener
 ******************/
connectBtn.addEventListener('click', (e) => {
  if (usernameInput.value === '') {
    alert('must write a name here before connecting');
  } else {
    let socket = new SockJS('http://localhost:8080/ws-stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
  }
});

const onConnected = (frame) => {
  setConnected(true);
  stompClient.subscribe('/all/messages', onMessageReceived);
  stompClient.subscribe('/all/messages', onPrivateMessage);
};

const onError = (error) => {
  console.error(error);
};

const onMessageReceived = (payload) => {
  displayResult(JSON.parse(payload.body));
};

function onPrivateMessage(payload) {
  displayResult(JSON.parse(payload.body));
}
/*****************
 * Event Listener
 *****************/
// (4) Take the value in the 'message-input' text field and send it to the server.
sendMessage.addEventListener('click', (e) => {
  let messageInput = document.getElementById('message-input');
  const messageToSend = {
    senderName: usernameInput.value,
    message: messageInput.value,
  };

  // this is the format of send:
  // stompClient.send(destination, callback, headers)
  stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));
  messageInput.value = '';
});

/*****************
 * Event Listener : This is for sending the message when pressing the Enter Key
 *****************/
document.getElementById('message-input').addEventListener('keyup', (e) => {
  let messageInput = document.getElementById('message-input');
  const messageToSend = {
    senderName: usernameInput.value,
    message: messageInput.value,
  };

  if (e.key === 'Enter' && messageInput !== '') {
    stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));
    messageInput.value = '';
  }
});

/******************
 * Event Listener
 ******************/
disconnect.addEventListener('click', (e) => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  usernameInput.value = '';
  displayMessages.innerHTML = '';
  setConnected(false);
});

/**********************************************************************
 *                Helper Methods
 **********************************************************************/
function displayResult(payload) {
  const p = document.createElement('p');
  p.innerHTML = `
      <label style="margin-right:2rem; font-size:1.7rem">${payload.senderName} : </label>  ${payload.message}`;
  displayMessages.appendChild(p);
}

function setConnected(connected) {
  if (connected) {
    disconnect.classList.toggle('hide');
    connectSection.classList.toggle('hide');
  } else {
    disconnect.classList.toggle('hide');
    connectSection.classList.toggle('hide');
  }
}
