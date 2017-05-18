package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.minhquan.foodyv1.Object.City;
import com.example.minhquan.foodyv1.R;

import java.util.ArrayList;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class ListViewCityAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<City> cityList;
    private int selectedCityId;
    private int selectedIndex;

    public ListViewCityAdapter(Context mContext, ArrayList<City> cityList, int selectedCityId) {
        this.mContext = mContext;
        this.cityList = cityList;
        this.selectedCityId = selectedCityId;
        for (int i = 0; i< cityList.size(); i++) {
            if(cityList.get(i).getId() == selectedCityId)
                selectedIndex =  i;
        }
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cityList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listview_text_item, parent, false);
        }
        else {
            itemView = convertView;
        }

        TextView textView = (TextView) itemView.findViewById(R.id.item_text);
        textView.setText(cityList.get(position).getName());

        if (position == selectedIndex)
            textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        else
            textView.setTextColor(mContext.getResources().getColor(R.color.textColorMain));

        return itemView;
    }
}
