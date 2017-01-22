package com.example.rouge.anem.Stage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.rouge.anem.Entity.Stage;
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

public class StageActivity extends AppCompatActivity {
    private ArrayList<Stage> listeStage;
    private Api myModel;
    private ListView listView;
    private Callback callback;
    private StageAdapter patientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);
        this.callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        myModel = new Api(this.callback, this);
        listeStage = new ArrayList<Stage>();
        listView = (ListView)findViewById(R.id.sListe);
        patientAdapter = new StageAdapter(getBaseContext(), listeStage);
        listView.setAdapter(patientAdapter);
        try {
            String[] mesparams = {Util.getProperty("url.stage", getBaseContext())};
            AsyncTask<String, String, Boolean> mThreadCon = new Api(this.callback, this).execute(mesparams);
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }

    public void didReceivedData(){
        ArrayList<HashMap<String,Object>> result = this.callback.getResult();
        listeStage = Stage.getStagesFromWS(result);
        patientAdapter.setListStage(listeStage);
        patientAdapter.notifyDataSetChanged();
        result = result;
    }

}
