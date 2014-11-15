package com.mti.verticles;

import org.vertx.java.platform.Verticle;

/**
 * Created by Yayap on 15/11/14.
 */
public class SchedulerVerticle extends Verticle {

    public void start() {
        container.deployWorkerVerticle("com.mti.verticles.ListenVerticle", 1);
        container.deployWorkerVerticle("com.mti.verticles.ConnectVerticle", 1);
        container.deployWorkerVerticle("com.mti.verticles.SearchUserVerticle", 1);
        container.deployWorkerVerticle("com.mti.verticles.MatchLogicVerticle", 1);
    }
}
