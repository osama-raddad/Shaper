package com.osama.shaper.dependencies;

import android.app.Application;

import timber.log.Timber;


public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}