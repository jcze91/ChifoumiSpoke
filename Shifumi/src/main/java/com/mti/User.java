package com.mti;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class User {

    @Getter
    @Setter
    private ArrayList<Match> matches;

    @Getter
    @Setter
    private Shot shot;

    public User() {
        this.matches = new ArrayList<Match>();
    }
}
