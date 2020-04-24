package com.example.marsplayassignment.di;

import com.example.marsplayassignment.HomePageRepository;
import com.example.marsplayassignment.WebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepositoryModule {

    @Singleton
    @Provides
    static WebService provideWebService() {
        return new WebService();
    }


    @Singleton
    @Provides
    static HomePageRepository provideHomeRepository(WebService webService) {
        return new HomePageRepository(webService);
    }
}
