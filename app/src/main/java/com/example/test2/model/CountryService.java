package com.example.test2.model;
import com.example.test2.di.DaggerDIComponent;

import java.util.List;
import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryService {
    private static CountryService service;
    private CountryService(){
        DaggerDIComponent.create().inject(this);
    }
    public static CountryService getInstance(){
        if(service == null){ service = new CountryService(); }
        return service;
    }
    @Inject
    public API api;
    public Single<List<CountryModel>> singleObservable;

    public Single<List<CountryModel>> getCountries(){
       singleObservable= api.getCountries();
        return singleObservable;
    }
}
