package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {
    FragmentFeatureManager fragmentFeatureManager;

    public FragmentFeatureManager getFragmentFeatureManager() {
        return fragmentFeatureManager;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentFeatureManager = FragmentFeatureManager.getInstance(this);

        create(savedInstanceState);
        fragmentFeatureManager.triggerOnCreate(savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        return inflater.inflate(getLayoutView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewCreated(view, savedInstanceState);

        fragmentFeatureManager.triggerOnViewCreated(view, savedInstanceState);
    }

    protected abstract void create(Bundle savedInstanceState);

    protected abstract void viewCreated(View view, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public abstract int getLayoutView();
}
