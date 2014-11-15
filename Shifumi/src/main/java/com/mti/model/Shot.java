package com.mti.model;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Shot {

    /**
     * Type du coup choisi
     */
    private ShotKind kind;

    /**
     * Set le type du coup
     * @param kind
     */
    public void setKind(ShotKind kind) {
        this.kind = kind;
    }

    /**
     *
     * @return le type du coup
     */
    public ShotKind getKind() {
        return kind;
    }

    /**
     * Liste contenant les types de coups qui battent
     * le coup choisi
     */
    private ArrayList<ShotKind> isCrushedBy;

    /**
     * Set la liste des types de coups qui battent
     * le coup choisi
     * @param isCrushedBy
     */
    public void setIsCrushedBy(ArrayList<ShotKind> isCrushedBy) {
        this.isCrushedBy = isCrushedBy;
    }

    /**
     *
     * @return la liste des types de coups qui battent
     * le coup choisi
     */
    public ArrayList<ShotKind> getIsCrushedBy() {
        return isCrushedBy;
    }

    /**
     * Liste contenant les types de coups qui sont
     * battus par le coup choisi
     */
    private ArrayList<ShotKind> crushes;

    /**
     * Set la liste contenant les types de coups qui sont
     * battus par le coup choisi
     * @param crushes
     */
    public void setCrushes(ArrayList<ShotKind> crushes) {
        this.crushes = crushes;
    }

    /**
     *
     * @return la liste contenant les types de coups qui sont
     * battus par le coup choisi
     */
    public ArrayList<ShotKind> getCrushes() {
        return crushes;
    }

    /**
     *
     * @return un string représentant le type choisi
     */
    @Override
    public String toString()
    {
        switch (kind){
            case PAPER:
                return "Paper";
            case ROCK:
                return "Rock";
            case SCISSORS:
                return "Scissors";
            case SPOKE:
                return "Spoke";
            case LIZARD:
                return "Lizard";
            default:
                return null;
        }
    }

    /**
     * Créer le Shot et initialise toutes les listes
     * en fonction du type choisi
     * @param kind
     */
    public Shot(ShotKind kind) {
        this.kind = kind;
        this.crushes = new ArrayList<ShotKind>();
        this.isCrushedBy = new ArrayList<ShotKind>();

        switch (kind){
            case PAPER:
                this.crushes.add(ShotKind.ROCK);
                this.crushes.add(ShotKind.SPOKE);

                this.isCrushedBy.add(ShotKind.SCISSORS);
                this.isCrushedBy.add(ShotKind.LIZARD);
                break;
            case ROCK:
                this.crushes.add(ShotKind.LIZARD);
                this.crushes.add(ShotKind.SCISSORS);

                this.isCrushedBy.add(ShotKind.SPOKE);
                this.isCrushedBy.add(ShotKind.PAPER);
                break;
            case SCISSORS:
                this.crushes.add(ShotKind.PAPER);
                this.crushes.add(ShotKind.LIZARD);

                this.isCrushedBy.add(ShotKind.ROCK);
                this.isCrushedBy.add(ShotKind.SPOKE);
                break;
            case SPOKE:
                this.crushes.add(ShotKind.SCISSORS);
                this.crushes.add(ShotKind.ROCK);

                this.isCrushedBy.add(ShotKind.PAPER);
                this.isCrushedBy.add(ShotKind.LIZARD);
                break;
            case LIZARD:
                this.crushes.add(ShotKind.PAPER);
                this.crushes.add(ShotKind.SPOKE);

                this.isCrushedBy.add(ShotKind.ROCK);
                this.isCrushedBy.add(ShotKind.SCISSORS);
                break;
        }
    }
}
