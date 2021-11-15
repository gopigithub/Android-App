package com.example.test2.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface API {
    @GET("DevTides/countries/master/countriesV2.json")
    public Single<List<CountryModel>> getCountries();
}
