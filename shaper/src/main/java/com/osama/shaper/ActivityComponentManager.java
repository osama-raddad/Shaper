package com.osama.shaper;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("unchecked")
public class ActivityComponentManager<T extends Activity> {
    private T activity;
    private List<ActivityComponent> featureList = new ArrayList<>();

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
        for (ActivityComponent component : featureList)
            component.onCreate(getCastedActivity(activity), mSavedInstanceState);
    }

    synchronized void triggerOnResume() {
        for (ActivityComponent component : featureList)
            component.onResume(getCastedActivity(activity));
    }

    synchronized void triggerOnStop() {
        for (ActivityComponent component : featureList)
            component.onStop(getCastedActivity(activity));
    }
}
