package com.andia.loice.fortuner.dagger.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.andia.loice.fortuner.BaseApp;
import com.andia.loice.fortuner.dagger.scheduler.IoScheduler;
import com.andia.loice.fortuner.dagger.scheduler.MainScheduler;
import com.andia.loice.fortuner.dagger.scheduler.SchedulerManager;
import com.andia.loice.fortuner.dagger.scheduler.SchedulerMngrImpl;
import com.andia.loice.fortuner.model.db.AppDatabase;
import com.andia.loice.fortuner.model.db.DataSource;
import com.andia.loice.fortuner.model.db.api.ApiService;
import com.andia.loice.fortuner.model.db.api.FortuneRepo;
import com.andia.loice.fortuner.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module(includes = {ViewModelModule.class})
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(BaseApp application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Reusable
    @MainScheduler
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Reusable
    @IoScheduler
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Reusable
    SchedulerManager provideSchedulerManager(@NonNull @MainScheduler final Scheduler mainThreadScheduler,
                                             @NonNull @IoScheduler final Scheduler ioScheduler) {
        return new SchedulerMngrImpl(mainThreadScheduler, ioScheduler);
    }

    @Provides
    @Reusable
    FortuneRepo providesFortuneRepo(@NonNull ApiService apiService,
                                    @NonNull DataSource datasource,
                                    @NonNull SchedulerManager schedulerMngr) {
        return new FortuneRepo(apiService, datasource, schedulerMngr);
    }

}
