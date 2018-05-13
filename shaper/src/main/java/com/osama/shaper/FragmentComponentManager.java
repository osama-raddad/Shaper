package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("unchecked")
public class FragmentComponentManager<T extends Fragment> {
    private T fragment;
    private List<FragmentComponent> featureList = new ArrayList<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

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
        disposables.add(Observable.fromIterable(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentFeature -> fragmentFeature.onCreateView(getCastedFragment(fragment), inflater, savedInstanceState)));
    }

    synchronized void triggerOnResume() {
        disposables.add(Observable.fromIterable(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentComponent -> fragmentComponent.onResume(getCastedFragment(fragment))));
    }

    public void triggerOnCreated(View view, Bundle savedInstanceState) {
        disposables.add(Observable.fromIterable(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(fragmentComponent -> fragmentComponent.onViewCreated(getCastedFragment(fragment), view, savedInstanceState)));
    }

    void destroy(){
        disposables.clear();
    }
}
