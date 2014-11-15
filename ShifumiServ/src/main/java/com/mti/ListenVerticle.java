package com.mti;


import io.vertx.rxcore.RxSupport;
import io.vertx.rxcore.java.eventbus.RxEventBus;
import io.vertx.rxcore.java.eventbus.RxMessage;
import io.vertx.rxcore.java.http.RxHttpServer;
import io.vertx.rxcore.java.http.RxHttpServerRequest;
import org.vertx.java.core.Handler;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;
import rx.Observable;
import rx.util.functions.Action1;
import rx.util.functions.Func1;

import java.util.Random;

import static rx.Observable.*;

public class ListenVerticle extends Verticle {

    public void start() {

        // load the general config object, loaded by using -config on command line
        JsonObject appConfig = container.config();

        final RxEventBus rxEventBus = new RxEventBus(vertx.eventBus());

        // setup Routematcher
        RouteMatcher matcher = new RouteMatcher();

        matcher.get("/createUser/:username", new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {
                String username = req.params().get("username");
                User user = new User(username);
                Lobby.getInstance().getUsers().add(user);
                JsonObject callback = new JsonObject().putBoolean("callback", true);
                req.response().putHeader("Content-Type", "application/json");
                req.response().end(callback.encodePrettily());
            }
        });
        matcher.get("/fetchOpponent", new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {
                final Random RANDOM = new Random();
                final int i = Lobby.getInstance()
                        .getUsers().size();
                User user = Lobby.getInstance().getUsers().get(RANDOM.nextInt(i));
                JsonObject callback = new JsonObject().putString("username", user.getName());
                req.response().putHeader("Content-Type", "application/json");
                req.response().end(callback.encodePrettily());
            }
        });
        matcher.get("/match/:username1/:shot1/:username2/:shot2", new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {
                String username1 = req.params().get("username1");
                String username2 = req.params().get("username2");
                String shot1 = req.params().get("shot1");
                String shot2 = req.params().get("shot2");

                User user1 = Lobby.getInstance().findUserByName(username1);
                User user2 = Lobby.getInstance().findUserByName(username2);
                user1.setShot(new Shot(ShotKindUtils.ConvertFromString(shot1)));
                user2.setShot(new Shot(ShotKindUtils.ConvertFromString(shot2)));
                Match m = new Match(user1, user2);
                user1.getMatches().add(m);
                user2.getMatches().add(m);
                m.doMatch();

                JsonObject callback = new JsonObject().putString("winner", m.getWinner().getName());
                req.response().putHeader("Content-Type", "application/json");
                req.response().end(callback.encodePrettily());
            }
        });
        // create and run the server
        HttpServer server = vertx.createHttpServer().requestHandler(matcher).listen(8888);

        // output that the server is started
        container.logger().info("Webserver started, listening on port: 8888");
    }


    /**
     * Simple handler that can be used to handle the reply from mongodb-persistor
     * and handles the 'more-exist' field.
     */
    private static class ReplyHandler implements Handler<Message<JsonObject>> {

        private final HttpServerRequest request;
        private JsonObject data;

        private ReplyHandler(final HttpServerRequest request, JsonObject data) {
            this.request = request;
            this.data = data;
        }

        @Override
        public void handle(Message<JsonObject> event) {
            // if the response contains more message, we need to get the rest
            if (event.body().getString("status").equals("more-exist")) {
                JsonArray results = event.body().getArray("results");

                for (Object el : results) {
                    data.getArray("results").add(el);
                }

                event.reply(new JsonObject(), new ReplyHandler(request, data));
            } else {

                JsonArray results = event.body().getArray("results");
                for (Object el : results) {
                    data.getArray("results").add(el);
                }

                request.response().putHeader("Content-Type", "application/json");
                request.response().end(data.encodePrettily());
            }
        }
    }
}
