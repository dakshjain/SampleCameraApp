package com.example.marsplayassignment.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.marsplayassignment.viewmodel.HomePageViewModel;
import com.example.marsplayassignment.viewmodel.PreviewHomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel.class)
    abstract ViewModel homepageViewModel(HomePageViewModel homePageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PreviewHomeViewModel.class)
    abstract ViewModel previewViewModel(PreviewHomeViewModel previewHomeViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
