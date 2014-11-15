package com.mti.verticles;

import com.mti.model.Lobby;
import com.mti.model.User;
import org.vertx.java.core.eventbus.impl.JsonObjectMessage;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

import java.util.Random;

/**
 * Created by Yayap on 15/11/14.
 */
public class SearchUserVerticle extends Verticle {

    public void start() {
        vertx.eventBus().registerHandler("waiting.user", message -> {
            JsonObjectMessage jobj = (JsonObjectMessage) message;
            final Random RANDOM = new Random();
            final int i = Lobby.getInstance().getUsers().size();

            User user = Lobby.getInstance().getUsers().get(RANDOM.nextInt(i));

            JsonObject obj = new JsonObject();
            obj.putString("message", "user found");
            obj.putString("name", user.getName());
            obj.putNumber("score", user.getScore());

        });
    }
}
