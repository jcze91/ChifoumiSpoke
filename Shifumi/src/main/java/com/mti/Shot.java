package com.mti;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Shot {
    @Getter
    @Setter
    private ShotKind kind;

    @Getter
    @Setter
    ArrayList<ShotKind> isCrushedBy;

    @Getter
    @Setter
    ArrayList<ShotKind> crushes;

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
