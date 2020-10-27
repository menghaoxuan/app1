package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class RateItem {
    String curName;
    String curRate;
    int id;

    public String getCurRate() {
        return curRate;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurRate(String curRate) {
        this.curRate = curRate;
    }
}
