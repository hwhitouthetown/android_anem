package com.example.rouge.anem.Entity;

/**
 * Created by rouge on 23/11/2016.
 */

public class Produit {
    private int id;
    private String nom;
    private float prix;
    private float prixAdherent;
    private String urlImange;

    public Produit(int id, String nom, float prix, float prixAdherent, String urlImange) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAdherent = prixAdherent;
        this.urlImange = urlImange;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrixAdherent() {
        return prixAdherent;
    }

    public void setPrixAdherent(float prixAdherent) {
        this.prixAdherent = prixAdherent;
    }

    public String getUrlImange() {
        return urlImange;
    }

    public void setUrlImange(String urlImange) {
        this.urlImange = urlImange;
    }
}
