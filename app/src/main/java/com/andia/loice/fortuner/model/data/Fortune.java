
package com.andia.loice.fortuner.model.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Fortune {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fortune")
    private String fortune;
    @ColumnInfo(name = "writer")
    private String writer;

    public Fortune(String fortune, String writer) {
        this.fortune = fortune;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFortune() {
        return fortune;
    }

    public void setFortune(String fortune) {
        this.fortune = fortune;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
