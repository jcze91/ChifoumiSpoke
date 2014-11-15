package com.mti.model;



import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Match {

    private User user1;
    private User user2;
    private User winner;

    public User getUser1() {
        return this.user1;
    }

    public void setUser1(User user) {
        this.user1 = user;
    }

    public User getUser2() {
        return this.user2;
    }

    public void setUser2(User user) {
        this.user2 = user;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getWinner() {
        return winner;
    }

    public Match(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    public void doMatch(){
        Shot shot1 = this.user1.getShot();
        Shot shot2 = this.user2.getShot();

        if (!shot1.getKind().equals(shot2.getKind())) {
            this.winner = shot1.getCrushes().contains(shot2.getKind()) ? this.user1 : this.user2;

            this.winner.setScore(this.winner.getScore() + 1);
        } else {
            this.winner = null;
        }
    }
}
