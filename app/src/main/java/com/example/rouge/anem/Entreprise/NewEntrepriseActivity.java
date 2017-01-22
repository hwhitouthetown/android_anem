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
    Entreprise entreprise = new Entreprise();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entreprise);
        Button button = (Button) findViewById(R.id.valider);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });
        Bundle b = getIntent().getExtras();
        if (b != null) {
            if (b.containsKey("entreprise")) {
                this.entreprise = (Entreprise) b.getSerializable("entreprise");
                fill();
            }
        }

    }

    private void fill(){
        ((EditText) findViewById(R.id.tel)).setText(entreprise.getNumTel());
        ((EditText) findViewById(R.id.nom)).setText(entreprise.getNom());
        ((EditText) findViewById(R.id.adresse)).setText(entreprise.getAdresse());
    }

    private void save(){
        entreprise.setNumTel(((EditText) findViewById(R.id.tel)).getText().toString());
        entreprise.setNom(((EditText) findViewById(R.id.nom)).getText().toString());
        entreprise.setAdresse(((EditText) findViewById(R.id.adresse)).getText().toString());
        Callback callback= new Callback<Void>() {
            public Void call() {
                finish();
                return null;
            }
        };
        try {
            Api api = new Api(callback, this);
            entreprise.save(api,getBaseContext());
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }
}
