package com.osama.shaper;

import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.ViewGroup;

import static android.animation.LayoutTransition.APPEARING;
import static android.animation.LayoutTransition.CHANGE_APPEARING;
import static android.animation.LayoutTransition.CHANGE_DISAPPEARING;
import static android.animation.LayoutTransition.CHANGING;
import static android.animation.LayoutTransition.DISAPPEARING;

public abstract class BaseActivity extends AppCompatActivity {
    protected ActivityComponentManager getActivityComponentManager() {
        return activityComponentManager;
    }

    private ActivityComponentManager activityComponentManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponentManager = ActivityComponentManager.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setupWindowAnimations();
        setupViewAnimation();

        ViewGroup mContainerView = findViewById(android.R.id.content);
        LayoutTransition lt = new LayoutTransition();
        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
        mContainerView.setLayoutTransition(lt);

        activityComponentManager.triggerOnCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        activityComponentManager.triggerOnResume();
    }

    @Override
    final protected void onStop() {
        super.onStop();
        activityComponentManager.triggerOnStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityComponentManager.destroy();
    }

    private void setupViewAnimation() {
        final ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();

        if (viewGroup != null) {
            LayoutTransition layoutTransition = new LayoutTransition();

            layoutTransition.enableTransitionType(APPEARING);
            layoutTransition.enableTransitionType(CHANGE_APPEARING);
            layoutTransition.enableTransitionType(CHANGE_DISAPPEARING);
            layoutTransition.enableTransitionType(CHANGING);
            layoutTransition.enableTransitionType(DISAPPEARING);

            layoutTransition.setDuration(APPEARING, 200);
            layoutTransition.setDuration(CHANGE_APPEARING, 200);
            layoutTransition.setDuration(CHANGE_DISAPPEARING, 200);
            layoutTransition.setDuration(CHANGING, 200);
            layoutTransition.setDuration(DISAPPEARING, 200);

            viewGroup.setLayoutTransition(layoutTransition);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Slide slideStart = new Slide(Gravity.START);
        slideStart.setDuration(200);
        getWindow().setEnterTransition(slideStart);
        getWindow().setReturnTransition(slideStart);

        Slide slideEnd = new Slide(Gravity.END);
        slideEnd.setDuration(200);
        getWindow().setExitTransition(slideEnd);
    }

}
