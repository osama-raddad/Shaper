package com.osama.shaper;

import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Toast;


import com.osama.shaper.dependencies.BaseActivityModule;
import com.osama.shaper.dependencies.BaseApplication;
import com.osama.shaper.dependencies.DaggerBaseActivityComponent;
import com.osama.shaper.dependencies.GithubRepo;
import com.osama.shaper.dependencies.network.GithubService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.animation.LayoutTransition.APPEARING;
import static android.animation.LayoutTransition.CHANGE_APPEARING;
import static android.animation.LayoutTransition.CHANGE_DISAPPEARING;
import static android.animation.LayoutTransition.CHANGING;
import static android.animation.LayoutTransition.DISAPPEARING;

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponentManager activityComponentManager;
    @Inject
    GithubService githubService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponentManager = ActivityComponentManager.getInstance(this);

        DaggerBaseActivityComponent.builder()
                .baseActivityModule(new BaseActivityModule(this))
                .baseApplicationComponent(BaseApplication.get(this).component())
                .build().inject(this);

                githubService.getAllRepos().enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(@NonNull Call<List<GithubRepo>> call, @NonNull Response<List<GithubRepo>> response) {
                Toast.makeText(BaseActivity.this,response.body().get(0).fullName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<GithubRepo>> call, @NonNull Throwable t) {

            }
        });

        onCreate(activityComponentManager,savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setupWindowAnimations();
        setupViewAnimation();


        ViewGroup mContainerView = findViewById(android.R.id.content);
        LayoutTransition lt = new LayoutTransition();
        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
        mContainerView.setLayoutTransition(lt);


        activityComponentManager.triggerOnCreate(savedInstanceState);
    }

    protected abstract void onCreate(@NonNull ActivityComponentManager featureManager, @Nullable Bundle savedInstanceState);

    @Override
    public final void onResume() {
        onResume(activityComponentManager);
        activityComponentManager.triggerOnResume();
        super.onResume();
    }

    private void onResume(@NonNull ActivityComponentManager componentManager) {

    }

    @Override
    final protected void onStop() {
        onStop(activityComponentManager);
        activityComponentManager.triggerOnStop();
        super.onStop();
    }

    protected void onStop(@NonNull ActivityComponentManager componentManager) {

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
