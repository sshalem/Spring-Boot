const connect = document.getElementById('connect');
const disconnect = document.getElementById('disconnect');
const sendMsg = document.getElementById('sendMsg');
const conversation = document.getElementById('conversation');

let stompClient = null;

function setConnected(connected) {
  if (connected) {
    // $('#conversation').show();
  } else {
    // $('#conversation').hide();
  }
  // $('#greetings').html('');
}

function disconnecting() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log('Disconnected');
}

connect.addEventListener('click', (e) => {
  let socket = new SockJS('http://localhost:8080/ws-stomp-endpoint');

  // (2) Create a new StompClient object with the WebSocket endpoint
  stompClient = Stomp.over(socket);

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

sendMsg.addEventListener('submit', (e) => {
  let messageInput = document.getElementById('message-input').value;

  console.log(messageInput);

  // const messageToSend = {
  //   message: messageInput,
  // };
  // stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));
});

disconnect.addEventListener('click', (e) => {
  disconnect();
});

function displayResult(message) {
  const messages = document.getElementById('messages');
  const p = document.createElement('p');
  p.innerHTML = 'message: ' + messages.message;
  response.appendChild(p);
}
