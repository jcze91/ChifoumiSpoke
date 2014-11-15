package com.mti.model;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yayap on 14/11/14.
 */
public class Lobby {

    /**
     * Liste contenant les user connectés au lobby
     */
    private ArrayList<User> users;

    /**
     * Instance du Singleton
     */
    private static Lobby instance = null;

    /**
     * Set la list d'users du lobby
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     *
     * @return la liste d'users connectés au lobby
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Constructeur privé pour l'implémentation
     * du Singleton
     */
    private Lobby() {
        this.users = new ArrayList<User>();
    }

    /**
     *
     * @return l'instance du Singleton ou
     * la créé si elle n'existe pas
     */
    public static Lobby getInstance()
    {
        if (instance == null)
            instance = new Lobby();
        return instance;
    }

    /**
     *
     * @param name
     * @return le user (dans la liste) dont le name = name
     */
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
