package com.mti;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.platform.Verticle;


/**
 * Created by Yayap on 15/11/14.
 */
public class ListenVerticle extends Verticle {

    public void start() {
        HttpServer server = vertx.createHttpServer();

        server.websocketHandler(new Handler<ServerWebSocket>() {
            public void handle(ServerWebSocket ws) {
                // A WebSocket has connected!
                if (ws.path().equals("/start")) {
                    ws.dataHandler(new Handler<Buffer>() {
                        public void handle(Buffer buffer) {
                            System.out.println(buffer.toString());
                        }
                    });
                }
            }
        }).listen(8080, "localhost");
    }
}
