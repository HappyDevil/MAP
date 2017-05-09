package com.map.mapmaxv1;

import android.app.Application;

import com.map.mapmaxv1.dto.DaoMaster;
import com.map.mapmaxv1.dto.DaoSession;

import org.greenrobot.greendao.database.Database;


public class MapApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DB.DATABASE_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}