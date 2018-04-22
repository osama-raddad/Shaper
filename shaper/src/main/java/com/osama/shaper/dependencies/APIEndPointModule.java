package com.osama.shaper.dependencies;

import dagger.Module;
import dagger.Provides;

@Module
public class APIEndPointModule {

    private final Object service;
    private final String baseUrl;

    public APIEndPointModule(String baseUrl, Object service) {
        this.baseUrl = baseUrl;
        this.service = service;
    }

    @Provides
    @BaseApplicationScope
    @ApplicationEndPoint
    public Object service() {
        return service;
    }


    @Provides
    @BaseApplicationScope
    @ApplicationEndPoint
    public String baseUrl() {
        return baseUrl;
    }

}
