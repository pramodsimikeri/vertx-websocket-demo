package com.demo.websocket;

import com.demo.websocket.verticle.WebSocketVerticle;
import io.vertx.core.Launcher;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Pramod
 * @version 1.0
 */
public class Starter {

    public static void main(final String[] args) {
        Launcher.executeCommand("run", WebSocketVerticle.class.getName());
    }
}
