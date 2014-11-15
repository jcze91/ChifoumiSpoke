package com.mti.verticles;

import com.mti.model.Lobby;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;


/**
 * Created by Yayap on 15/11/14.
 */
public class ListenVerticle extends Verticle {

    public void start() {
        Lobby.getInstance();
        HttpServer server = vertx.createHttpServer();

        /*server.websocketHandler(new Handler<ServerWebSocket>() {
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
        }).listen(8080, "localhost");*/


        server.requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest request) {
                System.out.println(request.method());

                request.bodyHandler((buffer) -> {
                    String str = buffer.getString(0, buffer.length());
                    JsonObject obj = new JsonObject(str);

                    String message = obj.getString("message");
                    if (message.equals("connect"))
                        vertx.eventBus().publish("user.add", obj);
                    if (message.equals("waiting user"))
                        vertx.eventBus().publish("waiting.user", obj);
                    if (message.equals("play match"))
                        vertx.eventBus().publish("play.match", obj);
                });

                request.response().end();
            }
        }).listen(8080, "localhost");
    }
}
