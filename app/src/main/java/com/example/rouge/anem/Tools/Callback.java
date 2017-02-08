package com.example.rouge.anem.Tools;

/**
 * Created by r0b on 04/01/17.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

public abstract class Callback<T> implements Callable<Void> {
    ArrayList<HashMap<String,Object>> result;

    public int code;

    void setResult (ArrayList<HashMap<String,Object>> result) {
        this.result = result;
    }

    public ArrayList<HashMap<String,Object>> getResult() {
        return result;
    }

    public abstract Void call ();
}
