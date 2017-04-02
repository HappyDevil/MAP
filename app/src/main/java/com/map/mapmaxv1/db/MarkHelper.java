package com.map.mapmaxv1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.map.mapmaxv1.Constants;
import com.map.mapmaxv1.dto.MarkDTO;

public class MarkHelper extends SQLiteOpenHelper {

    public MarkHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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

            ContentValues newValues = new ContentValues();

            newValues.put(Constants.MARK_DB.MARK_ID, markDTO.getId());
            newValues.put(Constants.MARK_DB.MARK_TEXT, markDTO.getText());
            newValues.put(Constants.MARK_DB.MARK_USER, markDTO.getUser());
            newValues.put(Constants.MARK_DB.MARK_DATE, String.valueOf(markDTO.getDate()));
            newValues.put(Constants.MARK_DB.MARK_PRICE, String.valueOf(markDTO.getPrice()));
            newValues.put(Constants.MARK_DB.MARK_LAT, String.valueOf(markDTO.getLat()));
            newValues.put(Constants.MARK_DB.MARK_LNG, String.valueOf(markDTO.getLng()));
            newValues.put(Constants.MARK_DB.MARK_TYPE, markDTO.getType());
            newValues.put(Constants.MARK_DB.MARK_FIO, markDTO.getFIO());
            newValues.put(Constants.MARK_DB.MARK_VISIBLE, String.valueOf(markDTO.isVisible()));
            newValues.put(Constants.MARK_DB.MARK_VERSION, 1);

            db.insert(Constants.MARK_DB.DATABASE_NAME, null, newValues);
        }
        catch (SQLiteException ex){
            Log.d("DB_EXCEPTION", "Not enough memory");
        }
    }
    public void updateMark(MarkDTO markDTO)
    {
        SQLiteDatabase db;
        try {
            db = this.getWritableDatabase();

            ContentValues newValues = new ContentValues();

            newValues.put(Constants.MARK_DB.MARK_ID, markDTO.getId());
            newValues.put(Constants.MARK_DB.MARK_TEXT, markDTO.getText());
            newValues.put(Constants.MARK_DB.MARK_USER, markDTO.getUser());
            newValues.put(Constants.MARK_DB.MARK_DATE, String.valueOf(markDTO.getDate()));
            newValues.put(Constants.MARK_DB.MARK_PRICE, String.valueOf(markDTO.getPrice()));
            newValues.put(Constants.MARK_DB.MARK_LAT, String.valueOf(markDTO.getLat()));
            newValues.put(Constants.MARK_DB.MARK_LNG, String.valueOf(markDTO.getLng()));
            newValues.put(Constants.MARK_DB.MARK_TYPE, markDTO.getType());
            newValues.put(Constants.MARK_DB.MARK_FIO, markDTO.getFIO());
            newValues.put(Constants.MARK_DB.MARK_VISIBLE, String.valueOf(markDTO.isVisible()));
            newValues.put(Constants.MARK_DB.MARK_VERSION, 1);

            db.insert(Constants.MARK_DB.DATABASE_NAME, null, newValues);
        }
        catch (SQLiteException ex){
            Log.d("DB_EXCEPTION", "Not enough memory");
        }
    }
}
