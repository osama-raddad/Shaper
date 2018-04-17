package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


public abstract class FragmentComponent<T extends Fragment> {

    public abstract void onCreate(T t, @Nullable Bundle savedInstanceState);

    public abstract void onViewCreated(T t, View view, @Nullable Bundle savedInstanceState);

}
