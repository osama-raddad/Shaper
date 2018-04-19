package com.osama.shaper.dependencies;

import com.osama.shaper.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseActivityModule {

    private final BaseActivity baseActivity;

    public BaseActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @BaseActivityScope
    public BaseActivity superActivity() {
        return baseActivity;
    }
}
