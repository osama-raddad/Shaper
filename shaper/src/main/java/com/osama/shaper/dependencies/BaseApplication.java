package com.osama.shaper.dependencies;

import android.app.Activity;
import android.app.Application;

import timber.log.Timber;


public class BaseApplication extends Application {

    private BaseApplicationComponent component;

    public static BaseApplication get(Activity activity) {
        return (BaseApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        component = DaggerBaseApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public BaseApplicationComponent component() {
        return component;
    }
}