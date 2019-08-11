package com.demo.websocket.handler;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;

import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author Pramod
 * @version 1.0
 */
public class WebSocketHandler implements Handler<BridgeEvent> {

    private final Vertx vertx;

    public WebSocketHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * Something has happened, so handle it.
     *
     * @param event the event to handle
     */
    @Override
    public void handle(BridgeEvent event) {
        if (event.type() == BridgeEventType.SOCKET_CREATED || event.type() == BridgeEventType.SEND) {
            sendRandomValueOverWebSocket();
        }
        event.complete(true);
    }

    private void sendRandomValueOverWebSocket() {

        //setting timer event to trigger after 5 seconds to induce delay in response
        vertx.setTimer(5000, event -> {
            vertx.eventBus().publish("socket.out", new Random().nextInt());
        });
    }
}
