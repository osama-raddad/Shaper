package com.osama.shaper;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java8.util.stream.StreamSupport;


@SuppressWarnings("unchecked")
public class ActivityComponentManager<T extends Activity> {
    private T activity;
    private List<ActivityComponent> components = new ArrayList<>();

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
        if (components != null)
            components.addAll(Arrays.asList(activityComponent));
        return this;
    }

    public ActivityComponentManager add(ActivityComponent activityComponent) {
        if (components != null)
            components.add(activityComponent);
        return this;
    }

    synchronized void triggerOnCreate(Bundle mSavedInstanceState) {
        for (ActivityComponent activityComponent : components)
            activityComponent.onCreate(getCastedActivity(activity), mSavedInstanceState);
    }

    synchronized void triggerOnResume() {
        for (ActivityComponent activityComponent : components)
            activityComponent.onResume(getCastedActivity(activity));

    }

    synchronized void triggerOnStop() {
        for (ActivityComponent activityComponent : components)
            activityComponent.onStop(getCastedActivity(activity));
    }
}
