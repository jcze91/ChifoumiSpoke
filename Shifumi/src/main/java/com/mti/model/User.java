package com.mti.model;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class User {

    private ArrayList<Match> matches;
    private Shot shot;
    private Integer score;
    private String name;

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public Shot getShot() {
        return shot;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User() {
        this.matches = new ArrayList<Match>();
        this.score = 0;
    }
}
