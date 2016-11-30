package com.example.rouge.anem.Entity;

/**
 * Created by rouge on 23/11/2016.
 */

public class Personnel extends Utilisateur {
    private int idPersonel;

    public Personnel(int id, String nom, String prenom, String identifiant, String password, int idPersonnel) {
        super(id, nom, prenom, identifiant, password);
        this.idPersonel = idPersonel;
    }
    public Personnel(int idPersonel) {
        this.idPersonel = idPersonel;
    }

    public int getIdPersonel() {
        return idPersonel;
    }

    public void setIdPersonel(int idPersonel) {
        this.idPersonel = idPersonel;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "idPersonel=" + idPersonel +
                '}';
    }
}
