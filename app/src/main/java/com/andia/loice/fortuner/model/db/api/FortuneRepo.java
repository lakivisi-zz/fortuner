package com.andia.loice.fortuner.model.db.api;

import com.andia.loice.fortuner.dagger.scheduler.SchedulerManager;
import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.model.data.FortuneResponse;
import com.andia.loice.fortuner.model.db.DataSource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Repository class for the Fortune object.
 * Facilitates getting the response from the API and storing them in the DB
 */
public class FortuneRepo {

    private final CompositeDisposable disposableManager = new CompositeDisposable();
    private ApiService apiService;
    private DataSource dataSource;
    private SchedulerManager schedulerMngrImpl;

    @Inject
    public FortuneRepo(ApiService apiService, DataSource dataSource, SchedulerManager schedulerMngrImpl) {
        this.apiService = apiService;
        this.dataSource = dataSource;
        this.schedulerMngrImpl = schedulerMngrImpl;
    }


    @NonNull
    public Flowable<Fortune> getAll() {
        fetchFromAPI();
        return dataSource.getFortuneLiveData();
    }

    private void fetchFromAPI() {

        List<String> defaultFortune = Arrays.asList("Good Luck today", "2015 Anon");
        dataSource.clearTable();
        disposableManager.add(
                apiService.getFortune()
                        .subscribeOn(schedulerMngrImpl.getIoScheduler())
                        .onErrorReturnItem(new FortuneResponse(defaultFortune))
                        .doOnComplete(() -> dataSource.insertFortune(constructFortune(defaultFortune)))
                        .subscribe(response -> dataSource.insertFortune(constructFortune(response.getmFortune()))));
    }


    @NonNull
    public Fortune constructFortune(List<String> fortuneResp) {
        String writer, fortune = "";
        int count = 0;

        if (fortuneResp.size() == 1) {
            return new Fortune(fortuneResp.get(0), "Anon");

        }

        writer = fortuneResp.get(fortuneResp.size() - 1);

        while (count <= (fortuneResp.size())) {
            fortune.concat(String.format(" {}", fortuneResp.get(count)));
        }

        return new Fortune(fortune, writer);
    }
}
