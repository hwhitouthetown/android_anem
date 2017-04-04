package com.example.rouge.anem.Main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rouge.anem.Entity.AuthenticatedUser;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyAccountActivity extends AppCompatActivity {

    private Callback callbackGetUser;
    private EditText inputNom;
    private EditText inputPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.callbackGetUser = new Callback<Void>() {
            public Void call() {
                didReceivedDatas();
                return null;
            }
        };

        this.inputNom = (EditText) findViewById(R.id.nom);
        this.inputPrenom = (EditText) findViewById(R.id.prenom);
        setContentView(R.layout.activity_my_account);

        getUserContent();
    }

    private  void didReceivedDatas() {
        ArrayList<HashMap<String, Object>> result = this.callbackGetUser.getResult();
        View focusView = null;

        if (result != null && this.callbackGetUser.code != 404) {

            String nomFromWs = result.get(0).get("Nom").toString();
            String prenomFromWs = result.get(0).get("Prenom").toString();

            inputNom.setText(nomFromWs);
            inputPrenom.setText(prenomFromWs);

        } else if (this.callbackGetUser.code == 0) {
            inputNom.setError(getString(R.string.server_contact));
            focusView = inputNom;
            focusView.requestFocus();
        }
    }

    private void getUserContent(){
        Api apiRegister = new Api(this.callbackGetUser,this);
        try {
            AuthenticatedUser.getInstance().getUserInfo(apiRegister,this,AuthenticatedUser.getInstance().getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

