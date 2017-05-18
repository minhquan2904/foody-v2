package com.example.minhquan.foodyv1.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.Object.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class ModelCity {
    private static final String DATABASE_NAME = "foody.db";
    private static final String TABLE_NAME = "CITY";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";

    private SQLiteDatabase db;

    public ModelCity(SQLiteDatabase db) {
        this.db = db;
    }
    private DBWebservices dbWebservices;
    public ModelCity(){dbWebservices = new DBWebservices("city");}

    public ArrayList<City> getAllCities()
    {
        dbWebservices.execute();
        ArrayList<City> list = new ArrayList<>();
        try {
            String data =dbWebservices.get();
            JSONArray array = new JSONArray(data);
            int count = array.length();
            for(int i=0;i<count;i++)
            {
                JSONObject object = array.getJSONObject(i);
                City city = new City();

                city.setId(object.getInt("id"));
                city.setName(object.getString("name"));

                list.add(city);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
    public boolean insertCity (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_ID+"="+id+"", null );
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public boolean updateCity (Integer id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                COLUMN_ID+" = ? ",
                new String[] { Integer.toString(id) });
    }
    */

    // lay tat ca
    public ArrayList<City> getCityList() {
        ArrayList<City> list = new ArrayList<>();

        Cursor res =  db.rawQuery( "select * from CITY", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            City city = new City(res.getInt(res.getColumnIndex("ID")),res.getString(res.getColumnIndex("NAME")));
            list.add(city);
            res.moveToNext();
        }

        res.close();
        return list;
    }

    // tim kiem
    public ArrayList<City> getCityList(String keyword) {
        ArrayList<City> list = new ArrayList<>();

        Cursor res =  db.rawQuery( "select * from CITY where NAME like '%"+keyword+"%'", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            City city = new City(res.getInt(res.getColumnIndex("ID")),res.getString(res.getColumnIndex("NAME")));
            list.add(city);
            res.moveToNext();
        }

        res.close();
        return list;
    }
}
