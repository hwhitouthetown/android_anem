package com.example.rouge.anem.Etudiant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.rouge.anem.Entity.Utilisateur;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rouge on 04/01/2017.
 */

public class EtudiantActivity extends AppCompatActivity {
    private ArrayList<Utilisateur> listeEtudiant;
    private Api myModel;
    private ListView listView;
    private Callback callback;
    private EtudiantAdapter patientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant);
        this.callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        myModel = new Api(this.callback);
        listeEtudiant = new ArrayList<Utilisateur>();
        listView = (ListView)findViewById(R.id.eListe);
        patientAdapter = new EtudiantAdapter(getBaseContext(), listeEtudiant);
        listView.setAdapter(patientAdapter);
        try {
            String[] mesparams = {Util.getProperty("url.etudiant", getBaseContext())};
            AsyncTask<String, String, Boolean> mThreadCon = new Api(this.callback).execute(mesparams);
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }

    public void didReceivedData(){
        ArrayList<HashMap<String,String>> result = this.callback.getResult();
        listeEtudiant = Utilisateur.getUtilisateursFromWS(result);
        patientAdapter.setListEtudiant(listeEtudiant);
        patientAdapter.notifyDataSetChanged();
        result = result;
    }

}
