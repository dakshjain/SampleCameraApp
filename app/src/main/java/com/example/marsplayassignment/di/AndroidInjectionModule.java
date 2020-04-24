package com.example.marsplayassignment.di;

import java.util.Map;

import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.internal.Beta;
import dagger.multibindings.Multibinds;

@Beta
@Module
public abstract class AndroidInjectionModule {
    @Multibinds
    abstract Map<Class<?>, AndroidInjector.Factory<?>> classKeyedInjectorFactories();

    @Multibinds
    abstract Map<String, AndroidInjector.Factory<?>> stringKeyedInjectorFactories();

    private AndroidInjectionModule() {
    }
}
