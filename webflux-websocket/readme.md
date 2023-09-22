# WebFlux WebSocket

Overview:

In this tutorial, I would like to show you Spring WebFlux WebSocket to enable a real-time communication between a client and the server.

Spring WebFlux WebSocket:

- Spring WebFlux is a non-blocking web stack to handle multiple concurrent requests with minimal number of threads and scale with fewer hardware resources. 
- WebSocket is a standardized way to enable a fully duplex / 2-way communication channel between a client and a server over a single TCP connection.

# Sample Application:
I am going to keep things very simple in this article in a way that a client would be sending a message to the server via WebSocket 
connection. The server would receive the string message and just uppercase it and respond to the client. Nothing else.

# Spring WebFlux WebSocket – Server Side:
We need to have an implementation for WebSocketSessionHandler and map the handler with specific URI. This WebSocket session handler is responsible for sending / receiving the messages to and from client.

- We could access the inbound messages from the receive method as Flux<WebSocketMessage>.
- We could process the message and send the response via send method

| Method                             | Description |
| -------------                      | ------------- |
| receive()                          | Provides access to the inbound message stream and completes when the connection is closed.  |
| send(Publisher<WebSocketMessage>)  |Takes a source for outgoing messages, writes the messages, and returns a Mono<Void> that completes when the source completes and writing is done.  |

````java
@Configuration
public class WebFluxConfig {
    @Autowired
    private WebFluxWebSocketHandler handler;

    @Bean
    public HandlerMapping handlerMapping(){
        final Map<String, WebFluxWebSocketHandler> handlerMap = Map.of("/uppercase", handler);
        return new SimpleUrlHandlerMapping(handlerMap, 1);
    }
}
````

````java
@Service
public class WebFluxWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        Flux<WebSocketMessage> stringFlux = webSocketSession.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(String::toUpperCase)
                .map(webSocketSession::textMessage);
        return webSocketSession.send(stringFlux);
    }

}
````


<img width="862" alt="Screenshot 2022-08-04 at 8 12 21 PM" src="https://user-images.githubusercontent.com/54174687/182876001-78d04116-8f10-46e2-a575-ff4caed18d8f.png">