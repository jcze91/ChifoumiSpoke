package com.mti;

import org.vertx.java.platform.Verticle;

/**
 * Created by Yayap on 15/11/14.
 */
public class SchedulerVerticle extends Verticle {

    public void start() {
        container.deployWorkerVerticle("com.mti.ListenVerticle", 1);
    }
}
