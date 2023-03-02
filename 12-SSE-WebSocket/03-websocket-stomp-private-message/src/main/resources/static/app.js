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
    // (1) Try to set up WebSocket connection with the handshake at "http://localhost:8080/ws-stomp-endpoint"
    let socket = new SockJS('http://localhost:8080/ws-stomp-endpoint');

    // (2) Create a new StompClient object with the WebSocket endpoint
    stompClient = Stomp.over(socket);

    // (3) in this function, we connect with STOMP , this will start:
    //     (a) connecting for establishing communications
    //     (b) Listening for url of '/all/messages'
    //     (c) Provide a callback , when the CONNECT frame arrives.
    //     (d) this is the format of connect: stompClient.connect(header, onConnected, onError);
    //          usually this header is empty object {} .

    stompClient.connect({}, onConnected, onError);
  }
});

const onConnected = (frame) => {
  setConnected(true);
  stompClient.subscribe('/all/messages', onMessageReceived);
};

const onError = (error) => {
  console.error(error);
};

const onMessageReceived = (payload) => {
  displayResult(JSON.parse(payload.body));
};
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
