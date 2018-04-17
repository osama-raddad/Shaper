package com.osama.shaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@SuppressWarnings("unchecked")
public class ActivityComponentManager<T extends Activity> {
    private T activity;
    private List<ActivityComponent> featureList = new ArrayList<>();

    private ActivityComponentManager(T activity) {
        this.activity = activity;
    }

    public static ActivityComponentManager getInstance(Activity activity) {
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
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(activityFeature -> {
                    if (activityFeature.getComponentView() > 0 && activityFeature.getComponentHostLayout() > 0) {
                        View rootView = LayoutInflater.from(activity).inflate(activityFeature.getComponentHostLayout(), activity.findViewById(activityFeature.getComponentView()), false);
                        ((ViewGroup) activity.findViewById(activityFeature.getComponentView())).addView(rootView);
                        activityFeature.setRootView((ViewGroup) rootView);
                    }
                    activityFeature.onCreate(getCastedActivity(activity), mSavedInstanceState);
                });
    }

    synchronized void triggerOnResume() {
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(activityFeature -> activityFeature.onResume(getCastedActivity(activity)));
    }

    synchronized void triggerOnStop() {
        Observable.from(featureList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e(FragmentComponent.class.getSimpleName(), throwable.getMessage(), throwable))
                .subscribe(activityFeature -> activityFeature.onStop(getCastedActivity(activity)));
    }


}
