package com.example.marsplayassignment.di;

import android.app.Application;
import android.content.Context;

import com.example.marsplayassignment.PlatformApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface ApplicationModule {

    @Binds
    Context bindContext(PlatformApplication application);

    @Singleton
    @Binds
    Application bindApplication(PlatformApplication application);
}
