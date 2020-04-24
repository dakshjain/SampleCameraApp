package com.example.marsplayassignment.di;

import com.example.marsplayassignment.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivity();

}
