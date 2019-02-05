package com.andia.loice.fortuner.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andia.loice.fortuner.model.data.Fortune;

import io.reactivex.Flowable;

@Dao
public interface FortuneDao {

    @Query("SELECT * FROM fortune")
    Flowable<Fortune> getFortune();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFortune(Fortune fortune);

    @Delete
    void deleteFortune(Fortune fortune);

    @Query("DELETE FROM fortune")
    void clearTable();

}
