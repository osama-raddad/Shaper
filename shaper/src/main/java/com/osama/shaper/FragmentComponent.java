package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;


public abstract class FragmentComponent<T extends Fragment> {
    private
    ComponentView componentView;
    private boolean isContentViewSet;


    private T fragment;
    private LayoutInflater inflater;

    public void onCreateView(@NonNull T fragment, LayoutInflater inflater, @Nullable Bundle mSavedInstanceState) {
        this.fragment = fragment;
        this.inflater = inflater;
    }

    public void onViewCreated(@NonNull T fragment, View view, @Nullable Bundle mSavedInstanceState) {

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
        if (componentView != null && !isContentViewSet) {
            isContentViewSet = true;
            return LayoutInflater.from(fragment.getActivity()).inflate(layout, componentView);
        } else return null;
    }

    public FragmentComponent<T> setComponentView(ComponentView componentView) {
        if (this.componentView == null) this.componentView = componentView;
        return this;
    }
}
