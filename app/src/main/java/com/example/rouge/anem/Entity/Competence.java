package com.example.rouge.anem.Entity;

/**
 * Created by rouge on 23/11/2016.
 */

public class Competence {
    private int id;
    private String titre;

    public Competence(int id, String titre) {
        this.id = id;
        this.titre = titre;
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
}
