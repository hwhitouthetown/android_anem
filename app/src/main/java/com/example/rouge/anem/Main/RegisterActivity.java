package com.example.rouge.anem.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rouge.anem.Entity.AuthenticatedUser;
import com.example.rouge.anem.R;
import com.example.rouge.anem.Tools.Api;
import com.example.rouge.anem.Tools.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText editEmail,editPassword,editRepeatPassword,editNom,editPrenom;
    private CheckBox checkBoxGCU;
    private Button registerButton;
    private Callback callbackRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initActivity();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });


        this.callbackRegister = new Callback<Void>() {
            public Void call() {
                didReceivedData();
                return null;
            }
        };


    }


    private void initActivity(){
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        editRepeatPassword = (EditText) findViewById(R.id.repeat_password);
        editNom = (EditText) findViewById(R.id.nom);
        editPrenom = (EditText) findViewById(R.id.prenom);
        checkBoxGCU = (CheckBox) findViewById(R.id.checkbox_gcu);
        registerButton = (Button) findViewById(R.id.email_register_button);


        final TextView textViewUrl = (TextView) findViewById(R.id.gcu);
        textViewUrl.setText( "\n" +getResources().getString(R.string.gcu_url_description) + ": " + getResources().getString(R.string.gcu_url));
        textViewUrl.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Linkify.addLinks(textViewUrl, Linkify.WEB_URLS);

    }


    private void didReceivedData(){

        ArrayList<HashMap<String,Object>> result = this.callbackRegister.getResult();
        View focusView = null;

        if(result !=null && this.callbackRegister.code != 404) {
            finish();
        } else if(this.callbackRegister.code == 0) {
            editEmail.setError(getString(R.string.server_contact));
            focusView = editEmail;
            focusView.requestFocus();
        } else {
            editEmail.setError(getString(R.string.email_already_use));
            focusView = editEmail;
            focusView.requestFocus();
        }

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {

        // Reset errors.
        editEmail.setError(null);
        editPassword.setError(null);
        editRepeatPassword.setError(null);
        editNom.setError(null);
        editPrenom.setError(null);



        // Store values at the time of the login attempt.
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String repeatPassword = editRepeatPassword.getText().toString();
        String nom = editNom.getText().toString();
        String prenom = editPrenom.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            editPassword.setError(getString(R.string.error_invalid_password));
            focusView = editPassword;
            cancel = true;
        } else if (!repeatPassword.equals(password)){
            editRepeatPassword.setError(getString(R.string.error_password_different));
            focusView = editRepeatPassword;
            cancel = true;
        }

        else if(TextUtils.isEmpty(nom)){
            editNom.setError(getString(R.string.error_invalid_nom));
            focusView = editNom;
            cancel = true;
        }

        else if(TextUtils.isEmpty(prenom)){
            editPrenom.setError(getString(R.string.error_invalid_prenom));
            focusView = editPrenom;
            cancel = true;
        }

        // Check for a valid email address.
        else if (TextUtils.isEmpty(email)) {
            editEmail.setError(getString(R.string.error_field_required));
            focusView = editEmail;
            cancel = true;
        } else if (!checkBoxGCU.isChecked()){
            checkBoxGCU.setError(getString(R.string.error_gcu_accept));
            focusView = checkBoxGCU;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            Api apiRegister = new Api(this.callbackRegister,this);

            try {

                AuthenticatedUser.getInstance().postRegister(apiRegister,this,password,email,nom,prenom);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private boolean isPasswordValid(String password) {
        return password.length() >= 5;
    }

}
