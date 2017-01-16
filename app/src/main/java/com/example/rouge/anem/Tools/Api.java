package com.example.rouge.anem.Tools;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by r0b on 04/01/17.
 */

public class Api extends AsyncTask<String, String, Boolean> {
    private Callback<Void> callback;
    private JsonParser jsonParser;
    private HashMap<String,String> parameters;
    private String method;
    ArrayList<HashMap<String,Object>> result;

    public Api(Callback<Void> callback){
        this.method = "GET";
        this.callback = callback;
        this.jsonParser = new JsonParser();
    }

    public Api(Callback<Void> callback, HashMap<String,String> parameters, String method){
        this.callback = callback;
        this.parameters = parameters;
        this.method = method;
        this.jsonParser = new JsonParser();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected Boolean doInBackground(String... urls) {
        URL url;
        StringBuilder bodyBuilder = new StringBuilder();
        try {
            url = new URL(urls[0]);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + urls[0]);
        }

        String body = "";

        if (this.method.equals("POST")) {

            for(Map.Entry<String, String> entry : this.parameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                bodyBuilder.append(key).append('=').append(value);
            }
            body = bodyBuilder.toString();

        }


        HttpURLConnection conn = null;
        try {
            Log.e("URL", "> " + url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setUseCaches(false);
            conn.setRequestMethod(this.method);
            byte[] bytes = new byte[0];
            if (this.method.equals("POST")) {
                Log.v("Sending", "Posting '" + body + "' to " + url);
                conn.setDoOutput(true);
                bytes = body.getBytes();
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                // post the request
                OutputStream out = conn.getOutputStream();
                out.write(bytes);
                out.close();
            }

            // handle the response
            int HttpResult = conn.getResponseCode();
            if (HttpResult == 200) {
                Log.v("Reponse serveur",conn.getInputStream().toString());
                this.result = jsonParser.readJsonStream(conn.getInputStream());
                return true;
            }else {
                throw new IOException("Request failed with error code " + HttpResult);
            }
        }
        catch (MalformedURLException e) {
            Log.e("Erreur", e.toString());
        } catch (java.net.SocketTimeoutException e) {
            Log.e("Erreur", e.toString());
        } catch (IOException e) {
            Log.e("Erreur", e.toString());

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
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
