package com.andia.loice.fortuner.dagger;


import com.andia.loice.fortuner.BaseApp;
import com.andia.loice.fortuner.dagger.module.AppModule;
import com.andia.loice.fortuner.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ActivityBuilder.class
})
public interface AppComponent extends AndroidInjector<BaseApp> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApp> {
    }
}
