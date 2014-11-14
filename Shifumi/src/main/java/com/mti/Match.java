package com.mti;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Match {

    @Getter
    @Setter
    private User user1;

    @Getter
    @Setter
    private User user2;

    @Getter
    @Setter
    private User winner;

    @Getter
    @Setter
    private Integer score;

    public Match(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    private void doMatch(){
       
    }
}
