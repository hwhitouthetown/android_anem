package com.example.rouge.anem.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rouge on 23/11/2016.
 */

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    public static ArrayList<Utilisateur> getUtilisateursFromWS(ArrayList<HashMap<String,String>> ws){
        ArrayList<Utilisateur> e = new ArrayList<Utilisateur>();
        for (HashMap<String, String> item: ws) {
            e.add(new Utilisateur(Integer.parseInt(item.get("id")), item.get("username"), item.get("username_canonical"), item.get("email")));
        }
        return e;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
