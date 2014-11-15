package com.mti.event;

import com.mvplite.event.ShowViewEvent;

/**
 * Created by JULIEN on 11/15/2014.
 */
public class ShowGameViewEvent extends ShowViewEvent {
    private final String username;
    private final String opponentName;

    public String getUsername()
    {
        return username;
    }
    public String getOpponentName()
    {
        return opponentName;
    }

    public ShowGameViewEvent(String username, String opponentName)
    {
        this.username = username;
        this.opponentName = opponentName;
    }
}