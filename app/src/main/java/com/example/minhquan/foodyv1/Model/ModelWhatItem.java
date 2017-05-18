package com.example.minhquan.foodyv1.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.minhquan.foodyv1.Object.WhatItem;
import com.example.minhquan.foodyv1.Object.WhereItem;

import java.util.ArrayList;

/**
 * Created by MinhQuan on 4/20/2017.
 */

public class ModelWhatItem{
        private static final String TABLE_NAME = "ITEM";
        private static final String COLUMN_ID = "ID";

        private SQLiteDatabase db;

        public ModelWhatItem(SQLiteDatabase sqLiteDatabase) {
            this.db = sqLiteDatabase;
        }

        public ArrayList<WhatItem> getItemList(String query) {
            ArrayList<WhatItem> list = new ArrayList<>();
            Cursor res =  db.rawQuery(query,null);
            if ((res.moveToFirst()) && res.getCount() > 0){
                while(!res.isAfterLast()){
                    WhatItem item = new WhatItem();

                    item.setName(res.getString(res.getColumnIndex("NAME")));
                    item.setAddress(res.getString(res.getColumnIndex("ADDRESS")));

                    item.setImg(res.getString(res.getColumnIndex("IMG")));
                    item.setCategoryId(res.getInt(res.getColumnIndex("CATEGORYID")));
                    item.setTypeId(res.getInt(res.getColumnIndex("TYPEID")));
                    item.setRestaurantId(res.getInt(res.getColumnIndex("RESTAURANTID")));
                    Log.w("Log","load item "+item.getName()+"");
                    //get type
                    String query2= "SELECT NAME FROM CATEGORYWHERE WHERE ID = "+item.getCategoryId()+"";
                    Log.w("Log","load item "+query2+"");
                    Cursor res2 = db.rawQuery(query2,null);
                    res2.moveToFirst();
                    item.setType(res2.getString(res2.getColumnIndex("NAME")));
                    res2.close();

                    list.add(item);
                    res.moveToNext();
                }
            }
            res.close();
            return list;
        }

        public ArrayList<WhatItem> findItemsByFields(int cityId, int districtId, int streetId, int categoryId) {
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
