package com.example.rouge.anem.Stage;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.Entity.Stage;
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

public class StageActivity extends Fragment {
    private ArrayList<Stage> listeStage;
    private Api myModel;
    private ListView listView;
    private Callback callback;
    private StageAdapter patientAdapter;
    private Entreprise entreprise;
    private Utilisateur etudiant;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.activity_stage, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        this.callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        myModel = new Api(this.callback, getContext());
        listeStage = new ArrayList<Stage>();
        listView = (ListView)getView().findViewById(R.id.sListe);
        patientAdapter = new StageAdapter(getContext(), listeStage);
        listView.setAdapter(patientAdapter);
        try {
            if (this.entreprise == null){
                String[] mesparams = {Util.getProperty("url.stage", getContext())};
                AsyncTask<String, String, Boolean> mThreadCon = new Api(this.callback, getContext()).execute(mesparams);
            }else{
                String[] mesparams = {Util.getProperty("url.stagebyentreprise", getContext())+entreprise.getId()};
                AsyncTask<String, String, Boolean> mThreadCon = new Api(this.callback, getContext()).execute(mesparams);
            }


        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }

    public void didReceivedData(){
        ArrayList<HashMap<String,Object>> result = this.callback.getResult();
        listeStage = Stage.getStagesFromWS(result);
        TextView empty = (TextView) getView().findViewById(R.id.empty);
        if (listeStage.size() > 0){
            empty.setVisibility(View.INVISIBLE);

            listView.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
        }
        patientAdapter.setListStage(listeStage);
        patientAdapter.notifyDataSetChanged();
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Utilisateur getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Utilisateur etudiant) {
        this.etudiant = etudiant;
    }
}
