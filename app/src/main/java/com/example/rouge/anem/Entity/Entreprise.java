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

    public static ArrayList<Entreprise> getEntreprisesFromWS(ArrayList<HashMap<String,Object>> ws){
        ArrayList<Entreprise> e = new ArrayList<Entreprise>();
        for (HashMap<String, Object> item: ws) {
            e.add(new Entreprise(Integer.parseInt((String)item.get("id")), (String)item.get("numtel"), (String)item.get("adresse"), (String)item.get("nom")));
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
