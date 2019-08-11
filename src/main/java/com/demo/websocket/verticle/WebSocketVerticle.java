package com.demo.websocket.verticle;

import com.demo.websocket.handler.WebSocketHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author Pramod
 * @version 1.0
 */
public class WebSocketVerticle extends AbstractVerticle {

    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in here.
     *
     * @throws Exception
     */
    @Override
    public void start() {

        Router router = Router.router(vertx);
        router.route("/socket/*").handler(socketHandler());
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    /**
     * <p>
     * <p>
     * </P>
     *
     * @return
     */
    private SockJSHandler socketHandler() {
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex("socket.out"))
                .addInboundPermitted(new PermittedOptions().setAddressRegex("socket.in"));
        WebSocketHandler socketHandler = new WebSocketHandler(vertx);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        return sockJSHandler.bridge(options, socketHandler);
    }
}
