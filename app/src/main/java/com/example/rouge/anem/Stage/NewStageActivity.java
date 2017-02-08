package com.example.rouge.anem.Stage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.rouge.anem.Entity.AuthenticatedUser;
import com.example.rouge.anem.Entity.Competence;
import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.Entity.Stage;
import com.example.rouge.anem.Entreprise.EntrepriseActivity;
import com.example.rouge.anem.Entreprise.NewEntrepriseContainer;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;
import com.example.rouge.anem.Tools.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NewStageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private Stage stage = new Stage();
    private EditText textEntreprise;
    private String[] etats = {"En attente","Validé", "Terminé"};
    private ArrayList<Competence>  competences;
    private ArrayList<Competence> mCompetences = new ArrayList<>();
    private RadioGroup group;
    private Api api;
    private Callback callback;
    private SearchView mSearchView;
    private ListView mListView;
    private TextView listComp;
    private ArrayAdapter<Competence> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_stage);
        group = (RadioGroup) findViewById(R.id.choixEtat);
        listComp = (TextView) findViewById(R.id.textComp);
        RadioButton button;
        for(int i = 0; i < etats.length; i++) {
            button = new RadioButton(this);
            button.setText(etats[i]);
            group.addView(button);
        }
        textEntreprise = (EditText) findViewById(R.id.textEntreprise);
        textEntreprise.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Intent myIntent = new Intent(NewStageActivity.this, EntrepriseActivity.class);
                    myIntent.putExtra("isSelecting", true);
                    startActivityForResult(myIntent, 0);
                }
            }
        });
        refreshComp();

    }

    public void handleSearch(){
        mSearchView = (SearchView) findViewById(R.id.search);
        mListView = (ListView) findViewById(R.id.competences);
        arrayAdapter = new ArrayAdapter<Competence>(this,
                android.R.layout.simple_list_item_1,
                competences);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Competence c = arrayAdapter.getItem(position);
                if (!mCompetences.contains(c)){
                    mCompetences.add(c);
                    didAddComp();
                }
            }
        });
        mListView.setTextFilterEnabled(true);
        setupSearchView();
    }

    public void didAddComp(){
        String s="";
        for (Competence c:mCompetences){
            s += c.getTitre() + "; ";
        }
        listComp.setText(s);
    }

    public void refreshComp(){
        this.callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        try {
            api = new Api(this.callback, this);
            String[] mesparams = {Util.getProperty("url.competences", getBaseContext())};
            api.execute(mesparams);
        }catch(IOException i ){
            Log.d("Erreur de propriété", i.toString());
        }
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Rechercher");
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText.toString());
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.plus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_plus) {
            save();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            Bundle b = data.getExtras();
            if (b.containsKey("entreprise")){
                stage.setEntreprise((Entreprise) b.getSerializable("entreprise"));
                textEntreprise.setText(stage.getEntreprise().getNom());
                Util.hideSoftKeyboard(this, textEntreprise);
            }

        }
    }

    public void didReceivedData(){
        ArrayList<HashMap<String,Object>> result = this.callback.getResult();
        competences = Competence.getCompetencesFromWS(result);
        handleSearch();
    }

    public void save(){
        EditText intitule = (EditText) findViewById(R.id.intitule);
        stage.setIntitule(intitule.getText().toString());
        EditText desc = (EditText) findViewById(R.id.textDesc);
        stage.setDescription(desc.getText().toString());
        stage.setEtat(etats[group.getCheckedRadioButtonId()]);
        stage.setCompetences(mCompetences);
        stage.setEtudiant(AuthenticatedUser.getInstance());
        Callback callback = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };
        Api api = new Api(callback,this);
        try {
            stage.save(api,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
