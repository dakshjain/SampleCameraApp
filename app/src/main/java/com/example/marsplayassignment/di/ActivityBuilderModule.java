package com.example.marsplayassignment.di;

import com.example.marsplayassignment.view.CameraView;
import com.example.marsplayassignment.view.HomeActivity;
import com.example.marsplayassignment.view.PreviewActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivity();

    @ContributesAndroidInjector
    abstract CameraView contributeCameraView();

    @ContributesAndroidInjector
    abstract PreviewActivity contributePreviewActivity();
}
