package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
    FragmentComponentManager fragmentComponentManager;

    public FragmentComponentManager getFragmentComponentManager() {
        return fragmentComponentManager;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponentManager = FragmentComponentManager.getInstance(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponentManager.triggerOnCreateView(inflater, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentComponentManager.triggerOnCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentComponentManager.triggerOnResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentComponentManager.destroy();
    }
}
