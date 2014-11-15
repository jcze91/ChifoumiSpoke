package com.mti.verticles;

import com.mti.model.Lobby;
import com.mti.model.User;
import org.vertx.java.core.eventbus.impl.JsonObjectMessage;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

/**
 * Created by Yayap on 15/11/14.
 */
public class ConnectVerticle extends Verticle {

    public void start() {
        vertx.eventBus().registerHandler("user.add", message -> {
            JsonObjectMessage jobj = (JsonObjectMessage) message;

            User user = new User(jobj.body().getString("username"));
            Lobby.getInstance().getUsers().add(user);

            JsonObject obj = new JsonObject();
            obj.putString("message", "connected");
            obj.putString("user", jobj.body().getString("username"));
        });
    }
}
