package com.example.minhquan.foodyv1.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.Object.Category;
import com.example.minhquan.foodyv1.Object.WhereItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class ModelWhereItem {
    private static final String TABLE_NAME = "ITEM";
    private static final String COLUMN_ID = "ID";

    private SQLiteDatabase db;

    public ModelWhereItem(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
    }


    DBWebservices dbWebservices ;

    public ModelWhereItem(){}

    public ArrayList<WhereItem> WSgetItemList(String keyword)
    {
        ArrayList<WhereItem> list = new ArrayList<>();
        dbWebservices= new DBWebservices("itemw"+keyword+"");
        dbWebservices.execute();

        try {
            String data = dbWebservices.get();

            JSONArray array = new JSONArray(data);
            int count = array.length();
            for(int i =0; i<count ;i++){

                JSONObject object = array.getJSONObject(i);
                WhereItem item = new WhereItem();
                // Log.d("KT!", object.toString());
                item.setAvgRating(object.getDouble("avgRating"));
                item.setName(object.getString("name"));
                item.setAddress(object.getString("address"));
                item.setTotalPictures(1000);
                item.setTotalReviews(object.getInt("noReview"));
                item.setImg(object.getString("imageUrl"));

                item.setTypeId(object.getInt("typeId"));
                item.setRestaurantId(object.getInt("id"));

                list.add(item);

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
    public ArrayList<WhereItem> WSfindItemsByFields(int cityId, int districtId, int streetId, int categoryId) {
        String key;
        if (categoryId <= 1){
            // no category (all)
            if (streetId > 0) {
               key = "?streetid="+streetId+"";
            }
            else if (districtId > 0) {
                // tim theo quan/huyen
                key = "?districtid="+districtId+"";
            }
            else if (cityId > 0) {
                key = "?cityid="+cityId+"";
            }
            else
                key="";   // lay het
        }
        else {
            // thim theo category
            if (streetId > 0) {
                // tim theo duong
               key="?categoryid="+categoryId+"&streetid="+streetId+"";
            }
            else if (districtId > 0) {
                // tim theo quan/huyen
                key="?categoryid="+categoryId+"&districtid="+districtId+"";
            }
            else if (cityId > 0) {
                key="?categoryid="+categoryId+"&cityid="+cityId+"";
            }
            else
                key="?categoryid="+categoryId+"";
        }
        return WSgetItemList(key);
    }

    public ArrayList<WhereItem> WSfindItemsByCity(int cityId) {
        String keyword= "cityd="+cityId+"";
        return getItemList(keyword);
    }
    public ArrayList<WhereItem> WSfindItemsByDistrict(int districtId) {
        String keyword= "districtid="+districtId+"";
        return getItemList(keyword);
    }
    public ArrayList<WhereItem> getItemList(String query) {
        ArrayList<WhereItem> list = new ArrayList<>();
        Cursor res =  db.rawQuery(query,null);
        if ((res.moveToFirst()) && res.getCount() > 0){
            while(!res.isAfterLast()){
                WhereItem item = new WhereItem();
                item.setAvgRating(res.getDouble(res.getColumnIndex("AVGRATING")));
                item.setName(res.getString(res.getColumnIndex("NAME")));
                item.setAddress(res.getString(res.getColumnIndex("ADDRESS")));
                item.setTotalPictures(res.getInt(res.getColumnIndex("TOTALPICTURES")));
                item.setTotalReviews(res.getInt(res.getColumnIndex("TOTALREVIEWS")));
                item.setImg(res.getString(res.getColumnIndex("IMG")));
                item.setCategoryId(res.getInt(res.getColumnIndex("CATEGORYID")));
                item.setTypeId(res.getInt(res.getColumnIndex("TYPEID")));
                item.setRestaurantId(res.getInt(res.getColumnIndex("RESTAURANTID")));

                list.add(item);
                res.moveToNext();
            }
        }
        res.close();
        return list;
    }

    public ArrayList<WhereItem> findItemsByCity(int cityId) {
        String query =  "select * from "+TABLE_NAME+ " where CITYID = "+cityId;
        return getItemList(query);
    }

    public ArrayList<WhereItem> findItemsByDistrict(int districtId) {
        String query =  "select * from "+TABLE_NAME+ " where DISTRICTID = "+districtId;
        return getItemList(query);
    }

    public ArrayList<WhereItem> findItemsByStreet(int streetId) {
        String query =  "select * from "+TABLE_NAME+ " where STREETID = "+streetId;
        return getItemList(query);
    }

    public ArrayList<WhereItem> findItemsByFields(int cityId, int districtId, int streetId, int categoryId) {
        String query;
        if (categoryId <= 1){
            // no category (all)
            if (streetId > 0) {
                // tim theo duong
                query =  "select * from "+TABLE_NAME+ " where STREETID = "+streetId;
            }
            else if (districtId > 0) {
                // tim theo quan/huyen
                query =  "select * from "+TABLE_NAME+ " where DISTRICTID = "+districtId;
            }
            else if (cityId > 0) {
                query =  "select * from "+TABLE_NAME+ " where CITYID = "+cityId;
            }
            else
                query =  "select * from "+TABLE_NAME;    // lay het
        }
        else {
            // thim theo category
            if (streetId > 0) {
                // tim theo duong
                query =  "select * from "+TABLE_NAME+ " where CATEGORYID="+categoryId+" and STREETID = "+streetId;
            }
            else if (districtId > 0) {
                // tim theo quan/huyen
                query =  "select * from "+TABLE_NAME+ " where CATEGORYID="+categoryId+" and DISTRICTID = "+districtId;
            }
            else if (cityId > 0) {
                query =  "select * from "+TABLE_NAME+ " where CATEGORYID="+categoryId+" and CITYID = "+cityId;
            }
            else
                query =  "select * from "+TABLE_NAME+" where CATEGORYID="+categoryId;    // lay het
        }
        return getItemList(query);
    }


}
