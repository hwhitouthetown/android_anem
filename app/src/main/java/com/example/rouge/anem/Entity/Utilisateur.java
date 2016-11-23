package com.example.rouge.anem.Entity;

/**
 * Created by rouge on 23/11/2016.
 */

public abstract class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String identifiant;
    private String password;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String identifiant, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.password = password;
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

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
