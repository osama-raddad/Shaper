package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@SuppressWarnings("unchecked")
public class FragmentFeatureManager<T extends Fragment> {
    private T fragment;
    private List<FragmentComponent> featureList = new ArrayList<>();

    private FragmentFeatureManager(T fragment) {
        this.fragment = fragment;
    }

    public static FragmentFeatureManager getInstance(Fragment fragment) {
        return new FragmentFeatureManager(fragment);
    }

    private T getCastedFragment(Fragment fragment) {
        return (T) fragment;
    }

    public FragmentFeatureManager add(FragmentComponent... fragmentComponents) {
        if (featureList != null)
            featureList.addAll(Arrays.asList(fragmentComponents));
        return this;
    }

    public FragmentFeatureManager add(FragmentComponent fragmentComponent) {
        if (featureList != null)
            featureList.add(fragmentComponent);
        return this;
    }


    synchronized void triggerOnCreate(@Nullable Bundle savedInstanceState) {
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentFeature -> fragmentFeature.onCreate(getCastedFragment(fragment), savedInstanceState));
    }

    synchronized void triggerOnViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentFeature -> fragmentFeature.onViewCreated(getCastedFragment(fragment), view, savedInstanceState));
    }
}
