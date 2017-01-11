package com.example.rouge.anem.Entreprise;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.Etudiant.EtudiantActivity;
import com.example.rouge.anem.Main.MainActivity;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EntrepriseActivity extends AppCompatActivity {
    private ArrayList<Entreprise> listeEntreprise;
    private Api myModel;
    private ListView listView;
    private Callback callback;
    private EntrepriseAdapter patientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);
        this.callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        myModel = new Api(this.callback);
        listeEntreprise = new ArrayList<Entreprise>();
        listView = (ListView)findViewById(R.id.lvListe);
        patientAdapter = new EntrepriseAdapter(getBaseContext(), listeEntreprise);
        listView.setAdapter(patientAdapter);
        //Utilisation de la classe API
        try {
            String[] mesparams = {Util.getProperty("url.entreprise", getBaseContext())};
            myModel.execute(mesparams);
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }

    public void didReceivedData(){
        ArrayList<HashMap<String,String>> result = this.callback.getResult();
        listeEntreprise = Entreprise.getEntreprisesFromWS(result);
        patientAdapter.setListEntreprise(listeEntreprise);
        patientAdapter.notifyDataSetChanged();
        result = result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.plus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_plus) {
            startActivity(new Intent(this, NewEntrepriseActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
