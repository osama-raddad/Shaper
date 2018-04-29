package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentComponentManager.triggerOnCreate(view,savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentComponentManager.triggerOnResume();
    }
}
