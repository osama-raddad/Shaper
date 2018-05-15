package com.osama.shaper;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;


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
            return LayoutInflater.from(activity).inflate(layout, componentView);
        } else return null;
    }

    public ActivityComponent<T> setComponentView(ComponentView componentView) {
        if (this.componentView == null) this.componentView = componentView;
        return this;
    }

    protected T getActivity() {
        return activity;
    }

    protected Context getContext() {
        return activity;
    }
}
