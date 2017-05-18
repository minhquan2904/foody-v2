package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhquan.foodyv1.R;

/**
 * Created by MinhQuan on 4/18/2017.
 */

public class GridViewMenuAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] labelList;
    private final int[] imageList;

    public GridViewMenuAdapter(Context c, String[] labelList, int[] imageList ) {
        mContext = c;
        this.labelList = labelList;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return labelList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView;
        ImageView imageView;

        if (convertView == null) {
            gridItem = inflater.inflate(R.layout.grid_home_item, parent, false);
            textView = (TextView) gridItem.findViewById(R.id.gridView_category_item_text);
            imageView = (ImageView) gridItem.findViewById(R.id.gridView_category_item_img);

        } else {
            gridItem = (View) convertView;
            textView = (TextView) convertView.findViewById(R.id.gridView_category_item_text);
            imageView = (ImageView) convertView.findViewById(R.id.gridView_category_item_img);
        }

        textView.setText(labelList[position]);
        imageView.setImageResource(imageList[position]);

        gridItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Tính năng chưa hoàn thiện !",Toast.LENGTH_SHORT).show();
            }
        });

        return gridItem;
    }
}
