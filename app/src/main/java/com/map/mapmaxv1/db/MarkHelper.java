package com.map.mapmaxv1.db.helpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.map.mapmaxv1.Constants;
import com.map.mapmaxv1.dto.MarkDTO;

public class MarkHelper extends SQLiteOpenHelper {

    private static int local;

    public MarkHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,int min,int local) {
        super(context, name, factory, version);
        MarkHelper.local =local;
    }

    public MarkHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("CREATE TABLE " + Constants.MARK_DB.DATABASE_NAME + " (" +
                Constants.MARK_DB.MARK_ID + " INTEGER PRIMARY KEY," + Constants.MARK_DB.MARK_TEXT + " TEXT," +
                Constants.MARK_DB.MARK_USER + " TEXT, " + Constants.MARK_DB.MARK_DATE + " INTEGER," +
                Constants.MARK_DB.MARK_PRICE + " INTEGER," + Constants.MARK_DB.MARK_LAT + " INTEGER," +
                Constants.MARK_DB.MARK_LNG + " TEXT," + Constants.MARK_DB.MARK_TYPE + " TEXT," +
                Constants.MARK_DB.MARK_FIO + " TEXT," + Constants.MARK_DB.MARK_VISIBLE + " INTEGER," +
                Constants.MARK_DB.MARK_VERSION + " INTEGER,");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void writeMark(MarkDTO markDTO)
    {
        SQLiteDatabase db;
        try {
            db = this.getWritableDatabase();
        }
        catch (SQLiteException ex){
        }
    }

}
