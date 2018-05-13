package com.osama.shaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("unchecked")
public class ActivityComponentManager<T extends Activity> {
    private T activity;
    private List<ActivityComponent> featureList = new ArrayList<>();
    private final CompositeDisposable disposables = new CompositeDisposable();


    private ActivityComponentManager(T activity) {
        this.activity = activity;
    }

    static ActivityComponentManager getInstance(Activity activity) {
        return new ActivityComponentManager(activity);
    }

    private T getCastedActivity(Activity activity) {
        return (T) activity;
    }

    public ActivityComponentManager add(ActivityComponent... activityComponent) {
        if (featureList != null)
            featureList.addAll(Arrays.asList(activityComponent));
        return this;
    }

    public ActivityComponentManager add(ActivityComponent activityComponent) {
        if (featureList != null)
            featureList.add(activityComponent);
        return this;
    }

    synchronized void triggerOnCreate(Bundle mSavedInstanceState) {
        disposables.add(Observable.fromIterable(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(activityFeature -> activityFeature.onCreate(getCastedActivity(activity), mSavedInstanceState)));
    }

    synchronized void triggerOnResume() {
        disposables.add(Observable.fromIterable(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(activityFeature -> activityFeature.onResume(getCastedActivity(activity))));
    }

    synchronized void triggerOnStop() {
        disposables.add(Observable.fromIterable(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(activityFeature -> activityFeature.onStop(getCastedActivity(activity))));
    }

    void destroy(){
        disposables.clear();
    }
}
