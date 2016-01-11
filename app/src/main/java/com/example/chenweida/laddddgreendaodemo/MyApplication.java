package com.example.chenweida.laddddgreendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.DaoMaster;
import com.example.greendao.DaoSession;

/**
 * Created by Chenweida on 2016/1/11.
 */
public class MyApplication extends Application {

    public DaoMaster daoMaster;
    public DaoSession daoSession;
    public DaoMaster.DevOpenHelper devOpenHelper;
    public SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        devOpenHelper = new DaoMaster.DevOpenHelper(this, "test", null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
