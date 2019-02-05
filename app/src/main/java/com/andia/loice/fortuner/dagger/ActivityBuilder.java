package com.andia.loice.fortuner.dagger;

import com.andia.loice.fortuner.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
