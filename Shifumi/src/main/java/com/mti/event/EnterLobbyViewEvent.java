package com.mti.event;

import com.mti.model.User;
import com.mvplite.event.ShowViewEvent;

/**
 * Created by JULIEN on 11/15/2014.
 */
public class EnterLobbyViewEvent extends ShowViewEvent {
    private final User user;

    public User getUser() {
        return user;
    }

    public EnterLobbyViewEvent(User user)
    {
        this.user = user;
    }

}