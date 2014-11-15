package com.mti.presenter;

import com.mti.event.ShowGameViewEvent;
import com.mti.event.ShowStartViewEvent;
import com.mti.view.LobbyView;
import com.mti.view.StartView;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by JULIEN on 11/15/2014.
 */

public class LobbyPresenter extends Presenter<LobbyView> implements Serializable {

    private boolean waitingForOpponent;

    public LobbyPresenter(LobbyView view, EventBus eventBus) {
        super(view, eventBus);
        bind();
        setWaitingForOpponent(true);
    }

    public void acceptOpponent(){
       getEventBus().fireEvent(new ShowGameViewEvent());
    }

    public void searchOpponent()
    {
        getView().searchOpponent();
        int maxTime = 10;
        int timeout = randInt(0, maxTime) * 1000;

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable(){
            @Override
            public void run(){
                getView().findOpponent();
            }
        }, timeout, TimeUnit.MILLISECONDS);
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