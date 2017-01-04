package com.example.rouge.anem.Tools;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.rouge.anem.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by r0b on 04/01/17.
 */

public class Api extends AsyncTask<String, String, Boolean> {
    private Callback<Void> callback;
    private JsonParser jsonParser;
    ArrayList<HashMap<String,String>> result;

    public Api(Callback<Void> callback){
        this.callback = callback;
        this.jsonParser = new JsonParser();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected Boolean doInBackground(String... urls) {
        try {

            //------------------>>
            HttpGet httppost = new HttpGet(urls[0]);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();

            if (status == 200) {
                HttpEntity entity = response.getEntity();

                this.result = jsonParser.readJsonStream(entity.getContent());

                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result){
            this.callback.setResult(this.result);
            this.callback.call();
        }
    }

    @Override
    protected void onProgressUpdate(String... param) {

    }

}
