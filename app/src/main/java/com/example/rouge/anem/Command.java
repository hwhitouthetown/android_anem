package com.example.rouge.anem;

/**
 * Created by rouge on 23/11/2016.
 */

public class Command {
    private int idProduit;
    private int idUtilisateur;
    private date dateAchat;

    public Command(int idProduit, date dateAchat, int idUtilisateur) {
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

    public date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
