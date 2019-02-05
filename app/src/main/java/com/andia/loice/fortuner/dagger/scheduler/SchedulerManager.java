package com.andia.loice.fortuner.dagger.scheduler;

import io.reactivex.Scheduler;

public interface SchedulerManager {
    Scheduler getIoScheduler();

    Scheduler getMainThreadScheduler();
}
