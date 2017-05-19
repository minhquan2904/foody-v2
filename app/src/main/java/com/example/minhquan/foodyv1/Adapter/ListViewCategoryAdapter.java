package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhquan.foodyv1.Object.Category;
import com.example.minhquan.foodyv1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class ListViewCategoryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Category> categories;
    private int selectedIndex;

    public ListViewCategoryAdapter(Context mContext, ArrayList<Category> categories) {
        this.mContext = mContext;
        this.categories = categories;
    }

    public void setSelectedIndex(int index){
        this.selectedIndex = index;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        Category currCategory = categories.get(position);

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.layout_image_item_text_tick, parent, false);
        }
        else
            itemView = convertView;

        ImageView imageView = (ImageView) itemView.findViewById(R.id.item_image);
        TextView textView = (TextView) itemView.findViewById(R.id.item_text);
        ImageView check = (ImageView) itemView.findViewById(R.id.item_check);

        if (currCategory.getImage() == null) {
            Log.d("Texddt", "NULL");
            imageView.setVisibility(GONE);
        }
        else {
            Log.d("Text",currCategory.getImage());
            Picasso.with(mContext).load(currCategory.getImage()).fit().centerCrop().into(imageView);
            imageView.setVisibility(VISIBLE);
        }
        textView.setText(currCategory.getName());
        if (position == selectedIndex)
            check.setVisibility(VISIBLE);
        else
            check.setVisibility(GONE);

        return itemView;
    }
}
