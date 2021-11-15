package com.example.test2.di;

import com.example.test2.model.API;
import com.example.test2.model.CountryService;
import com.example.test2.viewmodel.CountryViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {DIModule.class})
public interface DIComponent {
    void inject(CountryService service);
    void inject (CountryViewModel viewModel);
}
