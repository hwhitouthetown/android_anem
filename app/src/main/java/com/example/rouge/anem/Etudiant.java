package com.example.rouge.anem;

/**
 * Created by rouge on 23/11/2016.
 */

public class Etudiant extends Utilisateur {
    private String numEtudiant;

    public Etudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public Etudiant(int id, String nom, String prenom, String identifiant, String password, String numEtudiant) {
        super(id, nom, prenom, identifiant, password);
        this.numEtudiant = numEtudiant;
    }
}
