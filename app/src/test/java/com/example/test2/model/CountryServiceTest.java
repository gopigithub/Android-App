package com.example.test2.model;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.test2.viewmodel.CountryViewModel;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryServiceTest extends TestCase {

    private Single<List<CountryModel>> singleObservable;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    CountryService service;
    @InjectMocks
    CountryViewModel viewModel = new CountryViewModel();
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testNetworkCall(){

        List<CountryModel> countries = new ArrayList<CountryModel>();
        countries.add(new CountryModel("testCapital","testName","testRegion"));
        countries.add(new CountryModel("testCapital","testName","testRegion"));
        singleObservable = Single.just(countries);
        Mockito.when(service.getCountries()).thenReturn(singleObservable);

        viewModel.refresh();

        Assert.assertEquals(2,viewModel.countryLiveDataList.getValue().size());
    }
    @Before
    public void setupRxSchedule(){
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable ->{ runnable.run(); },true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }
}