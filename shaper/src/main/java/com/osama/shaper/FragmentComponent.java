package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class FragmentComponent<T extends Fragment> {
    private
    ComponentView componentView;


    private T fragment;

    public void onViewCreated(@NonNull T fragment, View rootView, @Nullable Bundle mSavedInstanceState) {
        this.fragment = fragment;
    }


    public void onResume(@NonNull T fragment) {

    }

    public void onStop(T fragment) {

    }

    public ComponentView getComponentView() {
        return componentView;
    }

    @Nullable
    protected View setContentView(@LayoutRes int layout) {
        if (componentView != null) {
            View rootView = LayoutInflater.from(fragment.getActivity()).inflate(layout, componentView);
            getComponentView().addView(rootView);
            return rootView;
        } else return null;
    }

    public FragmentComponent<T> setComponentView(ComponentView componentView) {
        if (this.componentView == null) this.componentView = componentView;
        return this;
    }
}
