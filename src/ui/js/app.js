function initialize() {
  registerHandler();
}

let eventBus;

function registerHandler() {
  eventBus = new EventBus('http://localhost:8080/socket');
  eventBus.onopen = function () {
    eventBus.registerHandler('socket.out', function (error, message) {
      const value = message.body;
      document.getElementById('server-value').innerHTML = value;
    });
  }
}

function getValue() {
  document.getElementById('server-value').innerHTML = 'fetching...';
  eventBus.send('socket.in')
}