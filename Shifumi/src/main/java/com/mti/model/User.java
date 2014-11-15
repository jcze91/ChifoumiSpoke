package com.mti.model;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class User {

    /**
     * Liste des matchs joués par l'user
     */
    private ArrayList<Match> matches;
    /**
     * Coup actuel
     */
    private Shot shot;
    /**
     * Score calculer avec toutes les parties
     * faites par l'user
     */
    private Integer score;
    /**
     * Nom du user
     */
    private String name;

    /**
     * Créé un User en settant son nom
     * @param username
     */
    public User(String username)
    {
        this.name = username;
    }

    /**
     * Set la liste des matchs du user
     * @param matches
     */
    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    /**
     *
     * @return la liste des matchs du user
     */
    public ArrayList<Match> getMatches() {
        return matches;
    }

    /**
     * Set le coup actuel du user
     * @param shot
     */
    public void setShot(Shot shot) {
        this.shot = shot;
    }

    /**
     *
     * @return le coup actuel du user
     */
    public Shot getShot() {
        return shot;
    }

    /**
     * Set le score du user
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     *
     * @return le score du joueur
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Set le nom du user
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return le nom du user
     */
    public String getName() {
        return name;
    }
}
