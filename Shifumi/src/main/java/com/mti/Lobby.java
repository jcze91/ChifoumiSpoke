package com.mti;

import lombok.Getter;
import lombok.Setter;
import org.atmosphere.config.service.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yayap on 14/11/14.
 */
public class Lobby {

    private ArrayList<User> users;
    private static Lobby instance = null;

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private Lobby() {
        this.users = new ArrayList<User>();
    }

    public static Lobby getInstance()
    {
        if (instance == null)
            instance = new Lobby();
        return instance;
    }

    public User findUserByName(String name) {
        Iterator<User> it = users.iterator();
        for (; it.hasNext();) {
            User u = it.next();
            if (u.getName().equals(name))
                return u;
        }
        return null;
    }

}
