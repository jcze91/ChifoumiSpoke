package com.mti.presenter;

import com.mti.event.EnterLobbyViewEvent;
import com.mti.event.ShowStartViewEvent;
import com.mti.model.Match;
import com.mti.model.Shot;
import com.mti.model.ShotKind;
import com.mti.model.User;
import com.mti.view.GameView;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by JULIEN on 11/15/2014.
 */

public class GamePresenter extends Presenter<GameView> implements Serializable {

    private User currentUser;
    private User IA;
    private Match m;
    private float timerShot;
    private ScheduledFuture future;

    public GamePresenter(GameView view, EventBus eventBus, User currentUser) {
        super(view, eventBus);
        bind();
        this.setCurrentUser(currentUser);
        this.IA = new User();
        IA.setName("IA");
        IA.setShot(new Shot(IAShot()));
    }
    public void goBackToLobby()
    {
        getEventBus().fireEvent(new EnterLobbyViewEvent(getCurrentUser()));
    }

    public void shot(ShotKind kind)
    {
        if (future != null)
            future.cancel(true);
        getCurrentUser().setShot(new Shot(kind));
        m = new Match(getCurrentUser(), IA);
        getCurrentUser().getMatches().add(m);
        IA.getMatches().add(m);
        m.doMatch();
        getView().displayWinner(getCurrentUser(), IA, m);
        getView().setBar(0f);
    }

    public void playNextShot()
    {
        getView().playNextShot();
        IA.setShot(new Shot(IAShot()));
        timerShot = 0f;
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (future != null)
            future.cancel(true);
        future = executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (timerShot < 5.0f) {
                    timerShot += 0.1f;
                    getView().increaseBar(0.02f);
                } else {
                    currentUser.setShot(new Shot(ShotKind.TIMEOUT));
                    m = new Match(getCurrentUser(), IA);
                    getCurrentUser().getMatches().add(m);
                    IA.getMatches().add(m);
                    m.doMatch();
                    getView().displayWinner(getCurrentUser(), IA, m);
                    getView().setBar(0f);
                    future.cancel(true);
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    private ShotKind IAShot() {
        final List<ShotKind> VALUES =
                Collections.unmodifiableList(Arrays.asList(ShotKind.values()));
        final int SIZE = VALUES.size();
        final Random RANDOM = new Random();

        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }

    private void bind(){
        getEventBus().addHandler(this);
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}