package com.mti.presenter;

import com.mti.model.Lobby;
import com.mti.model.User;
import com.mti.event.EnterLobbyViewEvent;
import com.mti.event.ShowStartViewEvent;
import com.mti.view.StartView;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;

import java.io.Serializable;

/**
 * Created by JULIEN on 11/15/2014.
 */

public class StartPresenter extends Presenter<StartView> implements Serializable {

    private static final long serialVersionUID = -9824276818586872L;


    public StartPresenter(StartView view, EventBus eventBus) {
        super(view, eventBus);
        bind();
    }

    public void enterLobby(String name){
        User u = new User();
        u.setName(name);
        Lobby.getInstance().getUsers().add(u);
        getEventBus().fireEvent(new EnterLobbyViewEvent(u));
    }

    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }

    private void bind(){
        getEventBus().addHandler(this);
    }



}