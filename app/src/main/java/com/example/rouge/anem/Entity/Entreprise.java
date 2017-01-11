package com.example.rouge.anem.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rouge on 23/11/2016.
 */

public class Entreprise {

    private int id;
    private String nom;
    private String adresse;
    private String numTel;

    public Entreprise(int id, String numTel, String adresse, String nom) {
        this.id = id;
        this.numTel = numTel;
        this.adresse = adresse;
        this.nom = nom;
    }

    public static ArrayList<Entreprise> getEntreprisesFromWS(ArrayList<HashMap<String,String>> ws){
        ArrayList<Entreprise> e = new ArrayList<Entreprise>();
        for (HashMap<String, String> item: ws) {
            e.add(new Entreprise(Integer.parseInt(item.get("id")), item.get("numtel"), item.get("adresse"), item.get("nom")));
        }
        return e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}
