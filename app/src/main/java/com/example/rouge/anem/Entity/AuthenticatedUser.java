package com.example.rouge.anem.Entity;

/**
 * Created by rouge on 16/01/2017.
 */
public class AuthenticatedUser {
    private static Utilisateur ourInstance = new Utilisateur(0, "", "", "");

    public static Utilisateur getInstance() {
        return ourInstance;
    }

    private AuthenticatedUser() {

    }
}
