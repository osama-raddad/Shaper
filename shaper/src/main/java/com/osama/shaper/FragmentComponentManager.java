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
public class FragmentComponentManager<T extends Fragment> {
    private T fragment;
    private List<FragmentComponent> featureList = new ArrayList<>();

    private FragmentComponentManager(T fragment) {
        this.fragment = fragment;
    }

    public static FragmentComponentManager getInstance(Fragment fragment) {
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


    synchronized void triggerOnCreate(@Nullable Bundle savedInstanceState) {
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentFeature -> fragmentFeature.onCreate(getCastedFragment(fragment), savedInstanceState));
    }

    synchronized void triggerOnResume() {
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentComponent -> fragmentComponent.onResume(getCastedFragment(fragment)));
    }
}
