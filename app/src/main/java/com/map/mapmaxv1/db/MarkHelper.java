package com.map.mapmaxv1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.map.mapmaxv1.Constants;
import com.map.mapmaxv1.dto.MarkDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarkHelper extends SQLiteOpenHelper
{

    private SQLiteDatabase db;
    private Date lastDate;
    private int minID;

    public Date getLastDate() {
        return lastDate;
    }

    public MarkHelper(Context context) {
        super(context, Constants.MARK_DB.DATABASE_NAME, null, Constants.MARK_DB.DATABASE_VERSION);
        this.openConnection();
        minID = 0;
        lastDate = null;
    }

    public void openConnection()
    {
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("CREATE TABLE " + Constants.MARK_DB.DATABASE_TABLE_NAME + " (" +
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

    public int writeMark(MarkDTO markDTO,boolean global)
    {
        try
        {
            ContentValues newValues = new ContentValues();

            if(global) newValues.put(Constants.MARK_DB.MARK_ID, markDTO.getId());
            else
            {
                minID--;
                newValues.put(Constants.MARK_DB.MARK_ID,minID);
            }
            newValues.put(Constants.MARK_DB.MARK_TEXT, markDTO.getText());
            newValues.put(Constants.MARK_DB.MARK_USER, markDTO.getUser());
            newValues.put(Constants.MARK_DB.MARK_DATE, String.valueOf(markDTO.getDate()));
            newValues.put(Constants.MARK_DB.MARK_PRICE, String.valueOf(markDTO.getPrice()));
            newValues.put(Constants.MARK_DB.MARK_LAT, String.valueOf(markDTO.getLat()));
            newValues.put(Constants.MARK_DB.MARK_LNG, String.valueOf(markDTO.getLng()));
            newValues.put(Constants.MARK_DB.MARK_TYPE, markDTO.getType());
            newValues.put(Constants.MARK_DB.MARK_FIO, markDTO.getFIO());
            newValues.put(Constants.MARK_DB.MARK_VISIBLE, String.valueOf(markDTO.isVisible()));

            db.insert(Constants.MARK_DB.DATABASE_TABLE_NAME, null, newValues);
        }
        catch (SQLiteException ex){
           //Log.d("DB_EXCEPTION", "Not enough memory");
        }
        return minID;
    }

    public void updateMark(MarkDTO markDTO)
    {
        try {
            ContentValues newValues = new ContentValues();

            newValues.put(Constants.MARK_DB.MARK_TEXT, markDTO.getText());
            newValues.put(Constants.MARK_DB.MARK_USER, markDTO.getUser());
            newValues.put(Constants.MARK_DB.MARK_DATE, String.valueOf(markDTO.getDate()));
            newValues.put(Constants.MARK_DB.MARK_PRICE, String.valueOf(markDTO.getPrice()));
            newValues.put(Constants.MARK_DB.MARK_LAT, String.valueOf(markDTO.getLat()));
            newValues.put(Constants.MARK_DB.MARK_LNG, String.valueOf(markDTO.getLng()));
            newValues.put(Constants.MARK_DB.MARK_TYPE, markDTO.getType());
            newValues.put(Constants.MARK_DB.MARK_FIO, markDTO.getFIO());
            newValues.put(Constants.MARK_DB.MARK_VISIBLE, String.valueOf(markDTO.isVisible()));

            db.update(Constants.MARK_DB.DATABASE_TABLE_NAME, newValues, "id = ?", new String[] { String.valueOf(markDTO.getId()) });
        }
        catch (SQLiteException ex){
            //Log.d("DB_EXCEPTION", "Not enough memory");
        }
    }

    public void deleteMark(MarkDTO markDTO)
    {
        try {
            db.delete(Constants.MARK_DB.DATABASE_TABLE_NAME, "id = " + String.valueOf(markDTO.getId()), null);
        }
        catch (SQLiteException ex){
            //Log.d("DB_EXCEPTION", "Memory blocked");
        }
    }

    public List<MarkDTO>  readMark(String[] columns,String selection,String[] selectionArgs, String groupBy,String having,String orderBy)
    {
        List<MarkDTO> mark= new ArrayList<>();
        MarkDTO m;
        try {
            Cursor c = db.query(Constants.MARK_DB.DATABASE_TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
            if(c.moveToFirst())
            {
                int idColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_ID);
                int textColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_TEXT);
                int userColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_USER);
                int dateColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_DATE);
                int priceColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_PRICE);
                int latColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_LAT);
                int lngColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_LNG);
                int typeColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_TYPE);
                int fioColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_FIO);
                int visibleColIndex = c.getColumnIndex(Constants.MARK_DB.MARK_VISIBLE);

                do {
                    m=new MarkDTO();
                    int id =  c.getInt(idColIndex);

                    if(minID>id) minID=id;

                    m.setId(id);
                    m.setText(c.getString(textColIndex));
                    m.setUser(c.getString(userColIndex));
                    Date date = new Date(c.getString(dateColIndex));

                    if((lastDate==null)||(lastDate.before(date))) lastDate=date;

                    m.setDate(date);
                    m.setPrice(c.getInt(priceColIndex));
                    m.setLat(c.getDouble(latColIndex));
                    m.setLng(c.getDouble(lngColIndex));
                    m.setType(c.getString(typeColIndex));
                    m.setFIO(c.getString(fioColIndex));
                    m.setVisible(Boolean.getBoolean(c.getString(visibleColIndex)));
                    mark.add(m);
                }while(c.moveToNext());
                c.close();
            }
        }
        catch (SQLiteException ex){
            //Log.d("DB_EXCEPTION", "Read error!");
        }
        return mark;
    }
}
