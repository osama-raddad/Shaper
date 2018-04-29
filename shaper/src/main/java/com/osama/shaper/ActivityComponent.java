package com.osama.shaper;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ActivityComponent<T extends Activity> {
    private
    ComponentView componentView;

    private T activity;

    public void onCreate(@NonNull T activity, @Nullable Bundle mSavedInstanceState) {
        this.activity = activity;
    }


    public void onResume(@NonNull T activity) {

    }

    public void onStop(T activity) {

    }

    public ComponentView getComponentView() {
        return componentView;
    }

    @Nullable
    protected View setContentView(@LayoutRes int layout) {
        if (componentView != null) {
            View rootView = LayoutInflater.from(activity).inflate(layout, componentView);
            getComponentView().addView(rootView);
            return rootView;
        } else return null;
    }

    public ActivityComponent<T> setComponentView(ComponentView componentView) {
        if (this.componentView == null) this.componentView = componentView;
        return this;
    }
}
