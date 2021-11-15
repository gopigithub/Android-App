package com.example.test2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test2.di.DaggerDIComponent;
import com.example.test2.model.CountryModel;
import com.example.test2.model.CountryService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends ViewModel {
    public MutableLiveData<List<CountryModel>> countryLiveDataList = new MutableLiveData<List<CountryModel>>();
    @Inject
    public CountryService service;
    public CountryViewModel(){
        super();
        DaggerDIComponent.create().inject(this);
    }
    private CompositeDisposable disposable = new CompositeDisposable();
    public void refresh(){
        getCountryList();
    }
    public MutableLiveData<List<CountryModel>> getRetrivedCountryLiveDataList(){
        return countryLiveDataList;
    }
    private void getCountryList(){
        disposable.add(service.getCountries()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
            @Override
            public void onSuccess(@NonNull List<CountryModel> countryModels) {
                countryLiveDataList.setValue(countryModels);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                List<CountryModel> list = new ArrayList<CountryModel>();
                CountryModel model = new CountryModel("Test","Test","Test");
                list.add(model);
                countryLiveDataList.setValue(list);
            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
