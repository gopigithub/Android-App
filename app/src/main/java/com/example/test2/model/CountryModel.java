package com.example.test2.model;



import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CountryModel {
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    @SerializedName("capital")
    String capital;
    @SerializedName("name")
    String name;
    @SerializedName("subregion")
    String subregion;

    public CountryModel(String capital, String name, String subregion) {
        this.capital = capital;
        this.name = name;
        this.subregion = subregion;
    }




}
