package com.example.rouge.anem.Entreprise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;

public class NewEntrepriseActivity extends AppCompatActivity {
    Entreprise entreprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entreprise);
        Button button = (Button) findViewById(R.id.valider);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newEntreprise();
            }
        });
    }

    private void newEntreprise(){
        String numTel = ((EditText) findViewById(R.id.tel)).getText().toString();
        String nom = ((EditText) findViewById(R.id.nom)).getText().toString();
        String adresse = ((EditText) findViewById(R.id.adresse)).getText().toString();
        entreprise = new Entreprise(0,numTel,adresse,nom);
        Callback callback= new Callback<Void>() {
            public Void call() {
                finish();
                return null;
            }
        };
        try {
            String[] mesparams = {Util.getProperty("url.entreprise", getBaseContext())};
            Api api = new Api(callback);
            api.execute(mesparams);
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }
}
