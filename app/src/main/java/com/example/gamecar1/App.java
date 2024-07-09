package com.example.gamecar1;

import android.app.Application;

import com.example.gamecar1.Utilities.SharedPreferencesManagerV3;
import com.example.gamecar1.Utilities.SignalManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManagerV3.init(this);
        SignalManager.init(this);
    }
}
