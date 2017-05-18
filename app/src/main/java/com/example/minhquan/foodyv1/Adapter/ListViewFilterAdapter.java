package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhquan.foodyv1.R;

/**
 * Created by MinhQuan on 4/18/2017.
 */

public class ListViewFilterAdapter extends BaseAdapter {
    private Context mContext;
    private String[] listViewNewsestLabelList;
    private int[] listViewNewsestDrawableList;
    private boolean[] listViewNewsestTagList;
    private int selectedIndex;

    public ListViewFilterAdapter(Context mContext, String[] listViewNewsestLabelList, int[] listViewNewsestDrawableList, boolean[] listViewNewsestTagList) {
        super();
        this.mContext = mContext;
        this.listViewNewsestLabelList = listViewNewsestLabelList;
        this.listViewNewsestDrawableList = listViewNewsestDrawableList;
        this.listViewNewsestTagList = listViewNewsestTagList;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listViewNewsestLabelList.length;
    }

    @Override
    public Object getItem(int position) {
        return listViewNewsestLabelList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            listItem = inflater.inflate(R.layout.layout_item_image_text_tag, parent, false);
        } else {
            listItem = convertView;
        }

        ImageView icon = (ImageView) listItem.findViewById(R.id.listView_filter_item_icon);
        TextView label = (TextView) listItem.findViewById(R.id.listView_filter_item_text);
        TextView tag = (TextView) listItem.findViewById(R.id.listView_filter_item_tag);

        icon.setImageResource(listViewNewsestDrawableList[position]);
        label.setText(listViewNewsestLabelList[position]);
        if (listViewNewsestTagList[position])
            tag.setVisibility(View.VISIBLE);
        else
            tag.setVisibility(View.GONE);

        if (position == selectedIndex){
            label.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            icon.setColorFilter(mContext.getResources().getColor(R.color.colorAccent));
        }
        else {
            label.setTextColor(mContext.getResources().getColor(R.color.textColorMain));
            icon.setColorFilter(mContext.getResources().getColor(R.color.textColorMain));
        }

        return listItem;
    }
}
