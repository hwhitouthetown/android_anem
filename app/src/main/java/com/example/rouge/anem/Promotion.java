package com.example.rouge.anem;

/**
 * Created by rouge on 23/11/2016.
 */

public class Promotion {
    private int id;
    private String titre;
    private String anneeDebut;
    private String anneeFin;
    private String niveau;

    public Promotion(int id, String titre, String anneeDebut, String anneeFin, String niveau) {
        this.id = id;
        this.titre = titre;
        this.anneeDebut = anneeDebut;
        this.anneeFin = anneeFin;
        this.niveau = niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(String anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public String getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(String anneeFin) {
        this.anneeFin = anneeFin;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
