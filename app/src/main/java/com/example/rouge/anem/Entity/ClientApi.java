package com.example.rouge.anem.Entity;

import java.io.Serializable;

/**
 * Created by hugosanslaville on 08/02/2017.
 */

public class ClientApi implements Serializable{

    private String identifiant;
    private String secret;
    private String token;
    private String token_type;
    private String refresh_token;

    public ClientApi(){

        this.identifiant = "";
        this.secret = "";
        this.token = "";
        this.token_type = "";
        this.refresh_token = "";
    }

    public ClientApi(String identifiant, String secret, String token, String token_type, String refresh_token) {
        this.identifiant = identifiant;
        this.secret = secret;
        this.token = token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
    }

    public ClientApi(String identifiant, String secret) {
        this.identifiant = identifiant;
        this.secret = secret;
        this.token = "";
        this.token_type = "";
        this.refresh_token = "";
    }


    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
