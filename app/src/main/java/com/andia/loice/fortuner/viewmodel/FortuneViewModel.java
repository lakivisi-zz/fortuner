package com.andia.loice.fortuner.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andia.loice.fortuner.dagger.scheduler.SchedulerManager;
import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.model.db.api.FortuneRepo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FortuneViewModel extends ViewModel {

    private FortuneRepo fortuneRepo;
    private SchedulerManager schedulerMngr;

    private MutableLiveData<Fortune> fortunes = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    FortuneViewModel(SchedulerManager schedulerMngr, FortuneRepo fortuneRepo) {
        this.fortuneRepo = fortuneRepo;
        this.schedulerMngr = schedulerMngr;
    }

    public MutableLiveData<Fortune> getFortunes() {
        disposableManager.add(
                fortuneRepo.getAll()
                        .subscribeOn(schedulerMngr.getIoScheduler())
                        .observeOn(schedulerMngr.getMainThreadScheduler())
                        .subscribe(this.fortunes::setValue));
        return fortunes;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposableManager.dispose();
    }
}
