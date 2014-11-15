package com.mti.model;


import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Shot {

    private ShotKind kind;

    public void setKind(ShotKind kind) {
        this.kind = kind;
    }

    public ShotKind getKind() {
        return kind;
    }

    private ArrayList<ShotKind> isCrushedBy;

    public void setIsCrushedBy(ArrayList<ShotKind> isCrushedBy) {
        this.isCrushedBy = isCrushedBy;
    }

    public ArrayList<ShotKind> getIsCrushedBy() {
        return isCrushedBy;
    }

    private ArrayList<ShotKind> crushes;

    public void setCrushes(ArrayList<ShotKind> crushes) {
        this.crushes = crushes;
    }

    public ArrayList<ShotKind> getCrushes() {
        return crushes;
    }

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
