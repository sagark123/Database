package com.mobilityindia;

import android.app.Application;

import com.firebase.client.Firebase;

public class MobilityIndia extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
