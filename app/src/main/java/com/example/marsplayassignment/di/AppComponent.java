package com.example.marsplayassignment.di;


import com.example.marsplayassignment.PlatformApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ViewModelModule.class,
                ActivityBuilderModule.class,
                RepositoryModule.class,
                ApplicationModule.class,
        }
)
public interface AppComponent {

@Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(PlatformApplication application);
    }


    void inject(PlatformApplication application);
}
