package com.mti.verticles;

import com.mti.model.*;
import org.vertx.java.core.eventbus.impl.JsonObjectMessage;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

import java.util.Random;

/**
 * Created by Yayap on 15/11/14.
 */
public class MatchLogicVerticle extends Verticle {
    public void start() {
        vertx.eventBus().registerHandler("play.match", message -> {
            JsonObjectMessage jobj = (JsonObjectMessage) message;

            User user1 = Lobby.getInstance().findUserByName(jobj.body().getString("username1"));
            User user2 = Lobby.getInstance().findUserByName(jobj.body().getString("username2"));
            user1.setShot(new Shot((ShotKind)jobj.body().getField("shot1")));
            user2.setShot(new Shot((ShotKind)jobj.body().getField("shot2")));

            Match m = new Match(user1, user2);
            user1.getMatches().add(m);
            user2.getMatches().add(m);
            m.doMatch();

            JsonObject obj = new JsonObject();
            obj.putString("message", "match done");
            obj.putString("winner", m.getWinner().getName());
        });
    }
}
