package com.mti.verticles;


import com.mti.model.*;
import io.vertx.rxcore.java.eventbus.RxEventBus;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

import java.util.Random;

public class ListenVerticle extends Verticle {

    public void start() {

        // Chargement de la config
        JsonObject appConfig = container.config();

        final RxEventBus rxEventBus = new RxEventBus(vertx.eventBus());

        // setup Routematcher
        RouteMatcher matcher = new RouteMatcher();

        //Creation du user
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
        //Recherche d'un adversaire
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
        //Déroulement d'un match
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
        // Créé et run le server
        HttpServer server = vertx.createHttpServer().requestHandler(matcher).listen(8888);

        container.logger().info("Webserver started, listening on port: 8888");
    }
}
