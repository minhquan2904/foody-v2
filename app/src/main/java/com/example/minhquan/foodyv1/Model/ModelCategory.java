package com.example.minhquan.foodyv1.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.Object.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class ModelCategory {
    private static final String DATABASE_NAME = "foody.db";
    public static final String TB_TYPE = "TYPE";
    public static final String TB_TYPE_ID = "ID";
    public static final String TB_TYPE_NAME = "NAME";
    public static final String TB_TYPE_IMG = "IMG";
    public static final String TB_TYPE_CATEGORYID = "CATEGORYID";
    public static final int TB_TYPE_FIRST_ID  = 232;

    private SQLiteDatabase db;
    public ModelCategory(SQLiteDatabase db){
        this.db = db;
    }

    private DBWebservices dbWebservices;

    public ModelCategory(){
        dbWebservices = new DBWebservices("category");
    }

    public ArrayList<Category> getCategory()
    {
        dbWebservices.execute();
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String data = dbWebservices.get();
            JSONArray array = new JSONArray(data);
            int count = array.length();
            for(int i =0; i<count ;i++){

                JSONObject object = array.getJSONObject(i);

                Log.d("KT!", object.toString());

                Category category = new Category();

                category.setId(object.getInt("id"));
                category.setName(object.getString("name"));
                category.setImage(object.getString("image"));
                categories.add(category);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  categories;
    }

    // lay tat ca
    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> list = new ArrayList<>();

        Cursor res =  db.rawQuery( "select * from CATEGORYWHERE", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Category category = new Category();
            category.setId(res.getInt(res.getColumnIndex("ID")));
            category.setName(res.getString(res.getColumnIndex("NAME")));
            category.setImage(res.getString(res.getColumnIndex("IMAGE")));
            list.add(category);
            res.moveToNext();
        }

        res.close();
        return list;
    }

}
