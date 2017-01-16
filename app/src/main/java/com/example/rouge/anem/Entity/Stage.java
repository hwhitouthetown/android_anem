package com.example.rouge.anem.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rouge on 23/11/2016.
 */

public class Stage {
    private int id;
    private String intitule;
    private String description;
    private String etat;
    private Entreprise entreprise;
    private Utilisateur etudiant;

    public Stage(int id, String intitule, String description, String etat, Entreprise entreprise, Utilisateur etudiant) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.etat = etat;
        this.entreprise = entreprise;
        this.etudiant = etudiant;
    }

    public static ArrayList<Stage> getStagesFromWS(ArrayList<HashMap<String,Object>> ws){
        ArrayList<Stage> e = new ArrayList<Stage>();
        for (HashMap<String, Object> item: ws) {
            e.add(new Stage(Integer.parseInt((String)item.get("id")), (String)item.get("intitule"), (String)item.get("description"), (String)item.get("etat"), Entreprise.getEntreprisesFromWS((ArrayList<HashMap<String,Object>>)item.get("identreprise")).get(0), Utilisateur.getUtilisateursFromWS((ArrayList<HashMap<String,Object>>)item.get("igetudiant")).get(0)));
        }
        return e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Utilisateur getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Utilisateur etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", description='" + description + '\'' +
                ", etat='" + etat + '\'' +
                ", entreprise=" + entreprise +
                ", etudiant=" + etudiant +
                '}';
    }
}
