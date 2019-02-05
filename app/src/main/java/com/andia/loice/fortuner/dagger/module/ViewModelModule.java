package com.andia.loice.fortuner.dagger.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.andia.loice.fortuner.dagger.ViewModelKey;
import com.andia.loice.fortuner.viewmodel.FortuneViewModel;
import com.andia.loice.fortuner.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FortuneViewModel.class)
    abstract ViewModel bindsFortuneViewModel(FortuneViewModel fortuneViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

}
