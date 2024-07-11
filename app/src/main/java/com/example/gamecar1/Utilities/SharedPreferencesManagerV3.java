package com.example.gamecar1.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManagerV3 {

    private static volatile SharedPreferencesManagerV3 instance = null;

    private static final String SP_FILE = "SP_FILE";
    private SharedPreferences sharedPref;

    private SharedPreferencesManagerV3(Context context) {
        this.sharedPref = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManagerV3 init(Context context){
        if (instance == null){
            synchronized (SharedPreferencesManagerV3.class){
                if (instance == null){
                    instance = new SharedPreferencesManagerV3(context);
                }
            }
        }
        return getInstance();
    }

    public static SharedPreferencesManagerV3 getInstance() {
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }
}
