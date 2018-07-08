package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

@SuppressWarnings("unchecked")
public class FragmentComponentManager<T extends Fragment> {
    private T fragment;
    private List<FragmentComponent> components = new ArrayList<>();

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
        if (components != null) {
            List<FragmentComponent> componentList = StreamSupport.parallelStream(Arrays.asList(fragmentComponents))
                    .map(fragmentComponent -> {
                        fragmentComponent.setFragment(fragment);
                        return fragmentComponent;
                    }).collect(Collectors.toList());
            components.addAll(componentList);
        }
        return this;
    }

    public FragmentComponentManager add(FragmentComponent fragmentComponent) {
        if (components != null) {
            fragmentComponent.setFragment(fragment);
            components.add(fragmentComponent);
        }
        return this;
    }

    void triggerOnCreate(@Nullable Bundle savedInstanceState) {
        for (FragmentComponent component : components) component.onCreate(savedInstanceState);
    }

    synchronized void triggerOnCreateView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        for (FragmentComponent component : components)
            component.onCreateView(getCastedFragment(fragment), inflater, savedInstanceState);
    }

    void triggerOnResume() {
        for (FragmentComponent component : components) component.onResume(getCastedFragment(fragment));
    }

    void triggerOnStop() {
        for (FragmentComponent component : components) component.onStop(getCastedFragment(fragment));
    }

    void triggerOnStart() {
        for (FragmentComponent component : components) component.onStart(getCastedFragment(fragment));
    }

    public void triggerOnCreated(View view, Bundle savedInstanceState) {
        for (FragmentComponent component : components)
            component.onViewCreated(getCastedFragment(fragment), view, savedInstanceState);
    }

    public void triggerOnDestroy() {
        for (FragmentComponent component : components) component.onDestroy(getCastedFragment(fragment));
    }
}
