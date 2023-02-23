// (1) Try to set up WebSocket connection with the handshake at "http://localhost:8080/ws-stomp-endpoint"
let socket = new SockJS('http://localhost:8080/ws-stomp-endpoint');

// (2) Create a new StompClient object with the WebSocket endpoint
let stompClient = Stomp.over(socket);

// (3) Start the STOMP communications, provide a callback for, when the CONNECT frame arrives.
//     this is the format of connect: stompClient.connect(header, onConnected, onError);
stompClient.connect(
	{ 'connection-Header': 'connection-Header' },
	function(frame) {
		console.log(frame);

		// this is the format of subscribe:
		// stompClient.subscribe(destination, callback, headers)
		stompClient.subscribe(
			'/all/messages',
			(result) => {
				show(JSON.parse(result.body));
			},
			{ 'send-Header': 'send-Header' }
		);
	},
	function(error) {
		console.error(error);
	}
);

// (4) Take the value in the 'message-input' text field and send it to the server with empty headers.
document.getElementById('sendMessage').addEventListener('click', (e) => {
	let messageInput = document.getElementById('message-input').value;
	const messageToSend = {
		message: messageInput,
	};

	// this is the format of send:
	// stompClient.send(destination, callback, headers)
	stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));
});

// This is helper method
function show(message) {
	const response = document.getElementById('messages');
	const p = document.createElement('p');
	p.innerHTML = 'message: ' + message.message;
	response.appendChild(p);
}