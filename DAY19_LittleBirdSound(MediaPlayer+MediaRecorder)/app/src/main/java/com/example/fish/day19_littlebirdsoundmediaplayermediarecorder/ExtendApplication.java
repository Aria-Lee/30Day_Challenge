package com.example.fish.day19_littlebirdsoundmediaplayermediarecorder;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class ExtendApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
