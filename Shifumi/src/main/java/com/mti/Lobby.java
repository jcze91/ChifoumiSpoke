package com.mti;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Yayap on 14/11/14.
 */
public class Lobby {

    private ArrayList<User> users;

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Lobby() {
        this.users = new ArrayList<User>();
    }

}
