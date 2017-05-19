package com.example.minhquan.foodyv1.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.Object.District;
import com.example.minhquan.foodyv1.Object.Street;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by MinhQuan on 4/18/2017.
 */

public class ModelDistrict  {
    private static final String TABLE_NAME = "DISTRICT";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";

    private SQLiteDatabase db;

    public ModelDistrict(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
    }

    public boolean insertDistrict (String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        return db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_ID+"="+id+"", null );
    }

    public int numberOfRows(){
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public boolean updateDistrict (Integer id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteDistrict (Integer id) {
        return db.delete(TABLE_NAME,
                COLUMN_ID+" = ? ",
                new String[] { Integer.toString(id) });
    }
    DBWebservices dbWebservices;
    public ModelDistrict(){}
    public ArrayList<District> WSgetDistrictList(int cityID) {
        ArrayList<District> list = new ArrayList<>();
        dbWebservices = new DBWebservices("district?cityid="+cityID+"");
        dbWebservices.execute();

        try {
            String data = dbWebservices.get();
            JSONArray array = new JSONArray(data);
            int count = array.length();
            for(int i=0;i<count;i++){
                JSONObject object = array.getJSONObject(i);
                District district = new District();

                district.setId(object.getInt("id"));
                district.setName(object.getString("name"));
                JSONArray arrayStr = object.getJSONArray("streetList");
                List<Street> streets = new ArrayList<>();
                int count2 = arrayStr.length();
                for (int j = 0 ; j< count2 ; j++)
                {
                    JSONObject object1 = arrayStr.getJSONObject(j);
                    Street street = new Street();
                    street.setId(object1.getInt("id"));
                    street.setName(object1.getString("name"));
                    street.setCityID(object1.getInt("cityID"));

                    streets.add(street);
                }
                district.setStreetList(streets);


                list.add(district);

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

    public List<Street> WSgetStreetByDistrictID(int districtID)
    {
        dbWebservices = new DBWebservices("street?districtid="+districtID+"");
        List<Street> list = new ArrayList<>();
        dbWebservices.execute();

        try {
            String data = dbWebservices.get();
            JSONArray array = new JSONArray(data);
            int count = array.length();
            for(int i=0;i<count;i++){
                JSONObject object = array.getJSONObject(i);
                Street street = new Street();

                street.setId(object.getInt("id"));
                street.setName(object.getString("name"));
                street.setCityID(object.getInt("cityID"));

                list.add(street);
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
    public ArrayList<District> getDistrictList(int cityID) {
        ArrayList<District> list = new ArrayList<>();

        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+ " where CITYID="+cityID, null );

        if ((res.moveToFirst()) && res.getCount() > 0){
            while(!res.isAfterLast()){
                District district = new District(res.getInt(res.getColumnIndex(COLUMN_ID)),res.getString(res.getColumnIndex(COLUMN_NAME)));

                // doc street list
                List<Street> streetList = new ArrayList<>();
                Cursor streetCur =  db.rawQuery( "select * from STREET where DISTRICTID = "+district.getId(), null );
                if ((streetCur.moveToFirst()) && streetCur.getCount() > 0){
                    while(!streetCur.isAfterLast()){
                        Street street = new Street(
                                streetCur.getInt(streetCur.getColumnIndex(COLUMN_ID)),
                                streetCur.getString(streetCur.getColumnIndex(COLUMN_NAME)));
                        streetList.add(street);
                        Log.w("db","District "+district.getName()+" add a street: "+street.getName());
                        streetCur.moveToNext();
                    }
                }
                district.setStreetList(streetList);
                list.add(district);
                Log.w("db","add a district: "+district.getName());
                streetCur.close();
                res.moveToNext();
            }
        }
        res.close();
        return list;
    }
}
