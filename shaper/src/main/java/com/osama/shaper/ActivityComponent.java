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


public abstract class ActivityComponent<T extends Activity> {
    private @IdRes
    int componentView;


    private @LayoutRes
    int componentHostLayout;
    private @Nullable
    ViewGroup rootView;
    private T activity;
    private boolean isCreated;

    public void onCreate(@NonNull T activity, @Nullable Bundle mSavedInstanceState) {
        this.activity = activity;
        isCreated = true;
    }


    public void onResume(@NonNull T activity) {

    }

    public void onStop(T activity) {

    }

    public int getComponentView() {
        return componentView;
    }

    public int getComponentHostLayout() {
        return componentHostLayout;
    }

    @Nullable
    protected View setComponentView(View view) throws Exception {
        if (!(getComponentView() > 0 && getComponentHostLayout() > 0))
            throw new Exception("the Host Component or Component Layout is not assigned");
        View rootView = LayoutInflater.from(activity).inflate(getComponentHostLayout(), activity.findViewById(getComponentView()), true);
        ((ViewGroup) activity.findViewById(getComponentView())).addView(view);
        setRootView((ViewGroup) rootView);
        return rootView;
    }

    public ActivityComponent<T> setComponentLayoutId(int componentView) throws Exception {
        if (this.componentHostLayout == 0) this.componentView = componentView;
        if (isCreated) setComponentView(activity.findViewById(componentView));
        return this;
    }

    public ActivityComponent<T> setComponentHostLayout(int componentHostLayout) {
        if (this.componentHostLayout == 0) this.componentHostLayout = componentHostLayout;
        return this;
    }

    @Nullable
    public ViewGroup getRootView() {
        return rootView;
    }

    public void setRootView(@Nullable ViewGroup rootView) {
        if (this.rootView == null)
            this.rootView = rootView;
    }
}
