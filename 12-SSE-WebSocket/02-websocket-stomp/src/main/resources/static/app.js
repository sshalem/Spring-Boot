const connect = document.getElementById('connect');
const disconnect = document.getElementById('disconnect');
const sendMessage = document.getElementById('sendMessage');
const conversation = document.getElementById('conversation');

let stompClient = null;

connect.addEventListener('click', (e) => {
  // (1) Try to set up WebSocket connection with the handshake at "http://localhost:8080/ws-stomp-endpoint"
  let socket = new SockJS('http://localhost:8080/ws-stomp-endpoint');

  // (2) Create a new StompClient object with the WebSocket endpoint
  stompClient = Stomp.over(socket);

  // (3) Start the STOMP communications, provide a callback for, when the CONNECT frame arrives.
  //     this is the format of connect: stompClient.connect(header, onConnected, onError);
  stompClient.connect(
    { 'connection-Header': 'connection-Header' },
    function (frame) {
      setConnected(true);
      console.log('Connected: ' + frame);
      stompClient.subscribe('/all/messages', (result) => {
        console.log(result);
        displayResult(JSON.parse(result.body));
      });
    },
    function (error) {
      console.error(error);
    }
  );
});

disconnect.addEventListener('click', (e) => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
});

// (4) Take the value in the 'message-input' text field and send it to the server.
sendMessage.addEventListener('click', (e) => {
  let messageInput = document.getElementById('message-input');
  const messageToSend = {
    message: messageInput.value,
  };

  // this is the format of send:
  // stompClient.send(destination, callback, headers)
  stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));

  messageInput.value = '';
});

// This eventListener is for sending the message when pressing the Enter Key
document.getElementById('message-input').addEventListener('keyup', (e) => {
  let messageInput = document.getElementById('message-input');
  const messageToSend = {
    message: messageInput.value,
  };

  if (e.key === 'Enter' && messageInput !== '') {
    stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));
    messageInput.value = '';
  }
});

// Helper Methods
function displayResult(msg) {
  const response = document.getElementById('messages');
  const p = document.createElement('p');
  p.innerHTML = 'message: ' + msg.message;
  response.appendChild(p);
}

function setConnected(connected) {
  if (connected) {
    disconnect.classList.toggle('hide');
    connect.classList.toggle('hide');
  } else {
    disconnect.classList.toggle('hide');
    connect.classList.toggle('hide');
  }
}
