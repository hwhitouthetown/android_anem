package com.example.rouge.anem.Entity;

import java.io.Serializable;
import android.content.Context;
import android.util.Log;

import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by rouge on 23/11/2016.
 */

public class Utilisateur implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private ClientApi clientApi;


    public Utilisateur() {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.clientApi = new ClientApi();
    }

    public Utilisateur (int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.clientApi = new ClientApi();
    }
    public static ArrayList<Utilisateur> getUtilisateursFromWS(ArrayList<HashMap<String,Object>> ws){
        ArrayList<Utilisateur> e = new ArrayList<Utilisateur>();
        for (HashMap<String, Object> item: ws) {
            e.add(new Utilisateur(Integer.parseInt(
                    (String)item.get("id")),
                    (String)item.get("username"), (String)item.get("username_canonical"),
                    (String)item.get("email")));
        }
        return e;
    }
    

    public void updateClient(String clientId,String clientSecret){

        this.getClientApi().setIdentifiant(clientId);
        this.getClientApi().setSecret(clientSecret);
    }


    public void updateClientFromWs(ArrayList<HashMap<String,Object>> result){

        if(result!=null){
            HashMap<String,Object> informations = result.get(0);

            Set keys = informations.keySet();

            ClientApi client = this.getClientApi();

            for(Object key  : keys){
                switch(key.toString()){

                    case "access_token":
                        client.setToken(informations.get(key).toString());
                        break;
                    case "token_type":
                         client.setToken_type(informations.get(key).toString());
                         break;

                    case "refresh_token":
                        client.setRefresh_token(informations.get(key).toString());
                        break;
                }
            }
        } else {
            Log.e("Erreur", "Impossible de mettre à jour résultat null");
        }
    }


    public void getClient(Api api, Context context) throws IOException {

        HashMap<String,String> param = new HashMap<>();

        api.setMethod("GET");
        api.setParameters(param);
        String[] url = {Util.getProperty("url.client.credential", context)};
        api.execute(url);
    }

    public void getConnect(Api api, Context context,String password,String username) throws IOException {

        HashMap<String,String> param = new HashMap<>();
        param.put("client_id",clientApi.getIdentifiant());
        param.put("client_secret",clientApi.getSecret());
        param.put("username",username);
        param.put("password",password);
        param.put("grant_type","password");

        api.setMethod("GET");
        api.setParameters(param);
        String[] url = {Util.getProperty("url.connect", context)};
        api.execute(url);
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientApi getClientApi() { return clientApi; }

    public void setClientApi(ClientApi clientApi) { this.clientApi = clientApi; }
}
