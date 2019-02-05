package com.andia.loice.fortuner.model.db;

import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.model.db.dao.FortuneDao;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class DataSource {
    private Flowable<Fortune> fortuneLiveData;
    private FortuneDao fortuneDao;

    @Inject
    DataSource(AppDatabase appDatabase) {
        fortuneDao = appDatabase.fortuneDao();
        fortuneLiveData = appDatabase.fortuneDao().getFortune();
    }

    public void insertFortune(Fortune fortune) {
        fortuneDao.insertFortune(fortune);
    }

    public void deleteFortune(Fortune fortune) {
        fortuneDao.deleteFortune(fortune);
    }

    public void clearTable() {
        fortuneDao.clearTable();
    }

    public Flowable<Fortune> getFortuneLiveData() {
        return fortuneLiveData;
    }
}
