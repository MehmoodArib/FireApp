package com.exe.mehmood.fireapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class FireApp extends Application {
    private static Context sContext = null;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        try {
            FirebaseApp.initializeApp(sContext);
        } catch (Exception e) {
            Log.e("FireApp", "Exception in firebase init " + e);
        }

    }
}
