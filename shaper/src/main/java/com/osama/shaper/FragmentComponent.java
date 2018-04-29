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
    private @IdRes
    int componentView;


    private @LayoutRes
    int componentHostLayout;
    private @Nullable
    ViewGroup rootView;
    View root;
    private T fragment;
    private boolean isCreated;


    public void onViewCreated(@NonNull T fragment, View root, @Nullable Bundle mSavedInstanceState) {
        this.fragment = fragment;
        this.root = root;
        isCreated = true;
    }


    public void onResume(@NonNull T fragment) {

    }

    public void onStop(T fragment) {

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
        View rootView = LayoutInflater.from(fragment.getActivity()).inflate(getComponentHostLayout(), root.findViewById(getComponentView()), true);
        ((ViewGroup) rootView.findViewById(getComponentView())).addView(view);
        setRootView((ViewGroup) rootView);
        return rootView;
    }

    public FragmentComponent<T> setComponentLayoutId(int componentView) throws Exception {
        if (this.componentHostLayout == 0) this.componentView = componentView;
        if (isCreated)
            setComponentView(root.findViewById(componentView));
        return this;
    }

    public FragmentComponent<T> setComponentHostLayout(int componentHostLayout) {
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
