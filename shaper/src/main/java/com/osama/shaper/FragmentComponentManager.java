package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class FragmentComponentManager<T extends Fragment> {
    private T fragment;
    private List<FragmentComponent> featureList = new ArrayList<>();

    private FragmentComponentManager(T fragment) {
        this.fragment = fragment;
    }

    static FragmentComponentManager getInstance(Fragment fragment) {
        return new FragmentComponentManager(fragment);
    }

    private T getCastedFragment(Fragment fragment) {
        return (T) fragment;
    }

    public FragmentComponentManager add(FragmentComponent... fragmentComponents) {
        if (featureList != null)
            featureList.addAll(Arrays.asList(fragmentComponents));
        return this;
    }

    public FragmentComponentManager add(FragmentComponent fragmentComponent) {
        if (featureList != null)
            featureList.add(fragmentComponent);
        return this;
    }


    synchronized void triggerOnCreateView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        for (FragmentComponent component : featureList)
            component.onCreateView(getCastedFragment(fragment), inflater, savedInstanceState);
    }

    synchronized void triggerOnResume() {
        for (FragmentComponent component : featureList)
            component.onResume(getCastedFragment(fragment));
    }

    public void triggerOnCreated(View view, Bundle savedInstanceState) {
        for (FragmentComponent component : featureList)
            component.onViewCreated(getCastedFragment(fragment), view, savedInstanceState);
    }
}
