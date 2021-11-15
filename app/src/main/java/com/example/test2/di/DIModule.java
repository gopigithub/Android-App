package com.example.test2.di;

import com.example.test2.model.API;
import com.example.test2.model.CountryService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DIModule {
    public static final String baseUrl = "https://raw.githubusercontent.com";
    @Provides
    public API getServiceAPI(){
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(API.class);
    }
    @Provides
    public CountryService getCountryService(){
        return CountryService.getInstance();
    }
}
