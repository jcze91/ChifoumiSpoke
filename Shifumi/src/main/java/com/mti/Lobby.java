package com.mti;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Lobby {
    @Getter
    @Setter
    private ArrayList<User> users;

    public Lobby() {
        this.users = new ArrayList<User>();
    }
}
