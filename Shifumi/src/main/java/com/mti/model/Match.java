package com.mti.model;

/**
 * Created by Yayap on 14/11/14.
 */
public class Match {

    /**
     * Premier user du match
     */
    private User user1;
    /**
     * Second user du match
     */
    private User user2;
    /**
     * Gagnant du match
     */
    private User winner;

    /**
     *
     * @return le premier utilisateur du match
     */
    public User getUser1() {
        return this.user1;
    }

    /**
     * Set le premier utilisateur du match
     * @param user
     */
    public void setUser1(User user) {
        this.user1 = user;
    }

    /**
     *
     * @return le second utilisateur du match
     */
    public User getUser2() {
        return this.user2;
    }

    /**
     * Set le second utilisateur du match
     * @param user
     */
    public void setUser2(User user) {
        this.user2 = user;
    }

    /**
     * Set le gagnant du match
     * @param winner
     */
    public void setWinner(User winner) {
        this.winner = winner;
    }

    /**
     *
     * @return le gagnant du match
     */
    public User getWinner() {
        return winner;
    }

    /**
     * Constructeur, créé un match et set ces deux users
     * @param user1
     * @param user2
     */
    public Match(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Calcul le gagnant du match et incremente le score de celui-ci
     */
    public void doMatch(){
        Shot shot1 = this.user1.getShot();
        Shot shot2 = this.user2.getShot();

        //On regarde si les deux coup
        if (!shot1.getKind().equals(shot2.getKind())) {
            //On regarde si le coup du user2 et dans la liste de coup qui battent le coup du user1
            //et on set le winner en fonction
            this.winner = shot1.getCrushes().contains(shot2.getKind()) ? this.user1 : this.user2;
            //On increémente le score du winner
            this.winner.setScore(this.winner.getScore() + 1);
        } else {
            this.winner = null;
        }
    }
}
