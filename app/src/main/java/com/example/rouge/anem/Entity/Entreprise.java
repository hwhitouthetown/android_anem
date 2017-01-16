package com.example.rouge.anem.Entity;

import android.content.Context;

import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rouge on 23/11/2016.
 */

public class Entreprise implements Serializable {

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
    public Entreprise() {
    }

    public static ArrayList<Entreprise> getEntreprisesFromWS(ArrayList<HashMap<String,Object>> ws){
        ArrayList<Entreprise> e = new ArrayList<Entreprise>();
        for (HashMap<String, Object> item: ws) {
            e.add(new Entreprise(Integer.parseInt((String)item.get("id")), (String)item.get("numtel"), (String)item.get("adresse"), (String)item.get("nom")));
        }
        return e;
    }

    public void save(Api api, Context context) throws IOException {
        HashMap<String,String> param = new HashMap<>();
        param.put("id",Integer.toString(id));
        param.put("nom",nom);
        param.put("tel",numTel);
        param.put("adresse",adresse);
        api.setMethod("POST");
        api.setParameters(param);
        String[] url = {Util.getProperty("url.update_entreprise", context)};
        api.execute(url);
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
