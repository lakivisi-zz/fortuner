package com.andia.loice.fortuner.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.model.db.dao.FortuneDao;

@Database(entities = {Fortune.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FortuneDao fortuneDao();
}