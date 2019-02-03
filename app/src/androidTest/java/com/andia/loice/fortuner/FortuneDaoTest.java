package com.andia.loice.fortuner;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.model.db.AppDatabase;
import com.andia.loice.fortuner.model.db.dao.FortuneDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FortuneDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;

    private FortuneDao fortuneDao;

    @Before
    public void setUp() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        fortuneDao = mDatabase.fortuneDao();
    }

    @After
    public void tearDown() {
        mDatabase.close();
    }

    @Test
    public void onFetchingfortune_shouldGetEmpty_IfTable_IsEmpty() {
        fortuneDao.getFortune().just(0)
                .test()
                .assertSubscribed()
                .assertValues(0)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void onInsertingFortune_checkIf_RowCountIsCorrect() {

        Fortune fortune = new Fortune("Good Luck", "2015 Anon");
        fortuneDao.insertFortune(fortune);

        fortuneDao.getFortune().just(1)
                .test()
                .assertSubscribed()
                .assertValues(1)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void flushFortuneTable() {

        Fortune fortune = new Fortune("Good Luck", "2015 Anon");
        fortuneDao.insertFortune(fortune);
        fortuneDao.getFortune().just(1)
                .test()
                .assertSubscribed()
                .assertValues(1)
                .assertComplete()
                .assertNoErrors();

        fortuneDao.clearTable();

        fortuneDao.getFortune().just(0)
                .test()
                .assertSubscribed()
                .assertValues(0)
                .assertComplete()
                .assertNoErrors();
    }
}
