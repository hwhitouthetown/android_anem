package com.example.rouge.anem.Entity;

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
