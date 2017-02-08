package com.example.rouge.anem.Entity;

import android.content.Context;

import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by rouge on 16/01/2017.
 */
public class AuthenticatedUser {
    private static Utilisateur ourInstance = new Utilisateur(1, "SANSLAVILLE", "Hugo", "rouger.baptiste@gmail.com");

    public static Utilisateur getInstance() {
        return ourInstance;
    }

    private AuthenticatedUser() {

    }
}
