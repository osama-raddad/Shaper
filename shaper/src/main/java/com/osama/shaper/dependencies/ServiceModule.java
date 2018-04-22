package com.osama.shaper.dependencies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osama.shaper.dependencies.network.DateTimeConverter;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {NetworkModule.class, APIEndPointModule.class})
public class ServiceModule {


    @Provides
    @BaseApplicationScope
    public Object githubService(Retrofit gitHubRetrofit, @ApplicationEndPoint Object service) {
        return gitHubRetrofit.create((Class) service);
    }

    @Provides
    @BaseApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @BaseApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson, @ApplicationEndPoint String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

}
