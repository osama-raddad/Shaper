package com.osama.shaper.dependencies;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @BaseApplicationScope
    @ApplicationContext
    public Context context() {
        return context;
    }
}
