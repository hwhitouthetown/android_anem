package com.example.rouge.anem.Entreprise;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;

public class NewEntrepriseActivity extends Fragment {
    Entreprise entreprise = new Entreprise();
    public NewEntrepriseActivity(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.activity_new_entreprise, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = (Button) getView().findViewById(R.id.valider);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });
        fill();
    }

    private void fill(){
        ((EditText) getView().findViewById(R.id.tel)).setText(entreprise.getNumTel());
        ((EditText) getView().findViewById(R.id.nom)).setText(entreprise.getNom());
        ((EditText) getView().findViewById(R.id.adresse)).setText(entreprise.getAdresse());
    }

    private void save(){
        entreprise.setNumTel(((EditText) getView().findViewById(R.id.tel)).getText().toString());
        entreprise.setNom(((EditText) getView().findViewById(R.id.nom)).getText().toString());
        entreprise.setAdresse(((EditText) getView().findViewById(R.id.adresse)).getText().toString());
        Callback callback= new Callback<Void>() {
            public Void call() {
                //finish();
                return null;
            }
        };
        try {
            Api api = new Api(callback, getContext());
            entreprise.save(api,getContext());
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }


}
