package com.example.rouge.anem;

import java.util.Date;

/**
 * Created by rouge on 23/11/2016.
 */

public class Commande {
    private int idProduit;
    private int idUtilisateur;
    private Date dateAchat;

    public Commande(int idProduit, Date dateAchat, int idUtilisateur) {
        this.idProduit = idProduit;
        this.dateAchat = dateAchat;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
