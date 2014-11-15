package com.mti.presenter;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mti.event.ShowGameViewEvent;
import com.mti.event.ShowStartViewEvent;
import com.mti.view.LobbyView;
import com.mti.view.StartView;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;
import org.json.JSONException;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by JULIEN on 11/15/2014.
 */

public class LobbyPresenter extends Presenter<LobbyView> implements Serializable {

    private boolean waitingForOpponent;
    private String username;
    private String opponentName;
    private ScheduledFuture schedFuture;

    public LobbyPresenter(LobbyView view, EventBus eventBus, String username) {
        super(view, eventBus);
        bind();
        setWaitingForOpponent(true);
        this.username = username;
    }

    public void acceptOpponent(){
       getEventBus().fireEvent(new ShowGameViewEvent(username, opponentName));
    }

    public void searchOpponent()
    {
        getView().searchOpponent();
        int rate = 500; // ms

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (schedFuture != null)
            schedFuture.cancel(true);
        schedFuture = executorService.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run(){
                Future<HttpResponse<JsonNode>> future = Unirest.get("http://localhost:8888/fetchOpponent/" + username)
                        .header("accept", "application/json")
                        .asJsonAsync(new Callback<JsonNode>() {

                            public void failed(UnirestException e) {
                            }

                            public void completed(HttpResponse<JsonNode> response) {
                                JsonNode body = response.getBody();
                                try {
                                   opponentName = (String)body.getObject().get("username");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                finally {
                                    getView().findOpponent();
                                    schedFuture.cancel(true);
                                }
                            }

                            public void cancelled() {
                            }

                        });
            }
        }, 0, rate, TimeUnit.MILLISECONDS);
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }

    private void bind(){
        getEventBus().addHandler(this);
    }


    public boolean isWaitingForOpponent() {
        return waitingForOpponent;
    }

    public void setWaitingForOpponent(boolean waitingForOpponent) {
        this.waitingForOpponent = waitingForOpponent;
    }
}