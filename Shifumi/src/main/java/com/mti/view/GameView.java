package com.mti.view;

import com.mti.model.Match;
import com.mti.model.User;
import com.mvplite.view.NavigateableView;

/**
 * Created by JULIEN on 11/14/2014.
 */
public interface GameView extends NavigateableView {
    public void playNextShot();
    public void displayWinner(User currentUser, User IA, Match m);
    public void increaseBar(float offset);
    public void setBar(float value);
}