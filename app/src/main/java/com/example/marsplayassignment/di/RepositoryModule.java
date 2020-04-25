package com.example.marsplayassignment.di;

import com.example.marsplayassignment.repository.HomePageRepository;
import com.example.marsplayassignment.repository.PreviewRepository;
import com.example.marsplayassignment.network.WebService;

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

    @Singleton
    @Provides
    static PreviewRepository providePreviewRepository(WebService webService) {
        return new PreviewRepository(webService);
    }
}
