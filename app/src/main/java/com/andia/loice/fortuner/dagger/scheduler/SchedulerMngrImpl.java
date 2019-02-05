package com.andia.loice.fortuner.dagger.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public class SchedulerMngrImpl implements SchedulerManager {

    private final Scheduler mainThreadScheduler;
    private final Scheduler ioScheduler;

    public SchedulerMngrImpl(@NonNull final Scheduler mainThreadScheduler,
                             @NonNull final Scheduler ioScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioScheduler = ioScheduler;
    }


    @Override
    public Scheduler getIoScheduler() {
        return ioScheduler;
    }

    @Override
    public Scheduler getMainThreadScheduler() {
        return mainThreadScheduler;
    }
}