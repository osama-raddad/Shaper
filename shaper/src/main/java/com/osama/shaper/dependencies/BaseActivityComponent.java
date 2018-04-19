package com.osama.shaper.dependencies;

import com.osama.shaper.BaseActivity;

import dagger.Component;

@BaseActivityScope
@Component(modules = BaseActivityModule.class, dependencies = BaseApplicationComponent.class)
public interface BaseActivityComponent {
    void inject(BaseActivity baseActivity);
}