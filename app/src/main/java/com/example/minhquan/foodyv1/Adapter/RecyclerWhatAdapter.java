package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.minhquan.foodyv1.Custom.ExpandableHeightGridView;
import com.example.minhquan.foodyv1.Object.WhatItem;
import com.example.minhquan.foodyv1.Object.WhereItem;
import com.example.minhquan.foodyv1.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class RecyclerWhatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_SLIDE = 0;
    public static final int TYPE_GRID = 1;
    public static final int TYPE_NULL = 2;
    public static final int TYPE_ITEM = 3;

    private Context mContext;
    private ArrayList<WhatItem> itemList;

    private BaseAdapter gridAdapter;    // adapter cho GridView
    private PagerAdapter slideAdapter;  // adapter cho ViewPager (Image Slide)

    public RecyclerWhatAdapter(Context mContext, ArrayList<WhatItem> itemList, BaseAdapter gridAdapter, PagerAdapter slideAdapter) {
        this.mContext = mContext;
        this.itemList = itemList;
        this.slideAdapter = slideAdapter;
        this.gridAdapter = gridAdapter;
    }

    @Override
    public int getItemViewType(int position)
    {
        switch (position) {
            case 0:
                return TYPE_SLIDE;
            case 1:
                return TYPE_GRID;
            case 2:
                return TYPE_NULL;
            default:
                return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size()+3;
    }

    @Override
    public long getItemId(int position) {
        if (position == getItemCount())
            return 0;
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //Log.w("log","onCreateViewHolder "+i);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (i) {
            case TYPE_SLIDE:
                view = inflater.inflate(R.layout.recycler_where_slide, parent, false);
                viewHolder = new ViewHolderSlide(view);
                break;
            case TYPE_GRID:
                view = inflater.inflate(R.layout.recycler_where_grid, parent, false);
                viewHolder = new ViewHolderGrid(view);
                break;
            case TYPE_NULL:
                view = inflater.inflate(R.layout.recycler_item_null, parent, false);
                viewHolder = new ViewHolderNull(view);
                break;
            case TYPE_ITEM:
                view = inflater.inflate(R.layout.recycler_what_item, parent, false);
                viewHolder = new ViewHolderItem(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        //Log.w("log","onBindViewHolder "+i + " - type"+viewHolder.getItemViewType());
        switch (viewHolder.getItemViewType()){
            case TYPE_SLIDE:
                final ViewHolderSlide viewHolderSlide = (ViewHolderSlide) viewHolder;
                viewHolderSlide.viewPager.setAdapter(slideAdapter);

                // thiet lap tu dong slide
                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    int currentPage = 0;
                    public void run() {
                        if (currentPage == (viewHolderSlide.viewPager.getChildCount()-1))
                            currentPage = 0;
                        else
                            currentPage++;
                        viewHolderSlide.viewPager.setCurrentItem(currentPage, true);
                    }
                };
                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(Update);
                    }
                }, 5000, 5000);
                break;

            case TYPE_GRID:
                ViewHolderGrid viewHolderGrid = (ViewHolderGrid) viewHolder;
                viewHolderGrid.gridView.setExpanded(true);
                viewHolderGrid.gridView.setAdapter(gridAdapter);
                break;

            case TYPE_NULL:
                ViewHolderNull viewHolderNull = (ViewHolderNull) viewHolder;
                if (itemList == null || itemList.size() == 0){
                    viewHolderNull.layout.setVisibility(View.VISIBLE);
                    viewHolderNull.imageView.setVisibility(View.VISIBLE);
                    viewHolderNull.textView.setVisibility(View.VISIBLE);
                }
                else {
                    viewHolderNull.layout.setVisibility(View.GONE);
                    viewHolderNull.imageView.setVisibility(View.GONE);
                    viewHolderNull.textView.setVisibility(View.GONE);
                }
                break;

            case TYPE_ITEM:
                ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
                WhatItem whatItem = itemList.get(i-3);
                //////////////////
                if (!whatItem.getImg().equals("")){
                   /* int imageResource = mContext.getResources().getIdentifier("fdi"+whatItem.getImg(), "drawable", mContext.getPackageName());
                    viewHolderItem.imgOneItem.setImageResource(imageResource);*/
                    Picasso.with(mContext).load(whatItem.getImg()).fit().into(((ViewHolderItem) viewHolder).imgOneItem, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Toast.makeText(mContext,"Can't load image",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Picasso.with(mContext).load("'https://media.foody.vn/usr/g16/154104/avt/c60/foody-avatar-721-636284560777357603.jpg").fit().into(((ViewHolderItem) viewHolder).imgOneItem);
                }
                viewHolderItem.textViewName.setText(whatItem.getName());
                viewHolderItem.textViewAddress.setText(whatItem.getAddress());
                viewHolderItem.textViewType.setText(whatItem.getType());
                viewHolderItem.txt_date_what_one_review.setText(whatItem.getDate());
                viewHolderItem.txt_name_what_one_review.setText(whatItem.getUsername());
                Picasso.with(mContext).load(whatItem.getUserimg()).fit().into(((ViewHolderItem) viewHolder).img_avatar_what_one_item);
                break;
        }
    }

    private static class ViewHolderSlide extends RecyclerView.ViewHolder{
        ViewPager viewPager;
        ViewHolderSlide(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager_slide);
        }
    }

    private static class ViewHolderGrid extends RecyclerView.ViewHolder {
        ExpandableHeightGridView gridView;
        ViewHolderGrid(View itemView) {
            super(itemView);
            gridView = (ExpandableHeightGridView) itemView.findViewById(R.id.gridView_category);
        }
    }

    private static class ViewHolderItem extends RecyclerView.ViewHolder {

//        TextView textViewAvgRating;
//        TextView textViewLabel;
//        TextView textViewAddress;
//        VideoView videoView ;
//        ImageView imageView;
//        LinearLayout buttonOrder;
//        TextView textViewComment;
//        TextView textViewPhoto;
//        TextView textViewStatus;
//        ImageView imageViewStatus;

        ImageView imgOneItem,img_avatar_what_one_item;
        TextView textViewType;
        TextView textViewName;
        TextView textViewAddress,txt_name_what_one_review,txt_date_what_one_review;




        ViewHolderItem(View v) {
            super(v);

            textViewAddress = (TextView) v.findViewById(R.id.txt_address_what_one_item);

            imgOneItem = (ImageView) v.findViewById(R.id.img_what_one_item);

            textViewType = (TextView) v.findViewById(R.id.txt_type_what_one_item);
            textViewName = (TextView) v.findViewById(R.id.txt_name_what_one_item);
            img_avatar_what_one_item = (ImageView) v.findViewById(R.id.img_avatar_what_one_item);
            txt_name_what_one_review = (TextView) v.findViewById(R.id.txt_name_what_one_review);
            txt_date_what_one_review = (TextView) v.findViewById(R.id.txt_date_what_one_review);
        }
    }

    private static class ViewHolderNull extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView imageView;
        TextView textView;

        ViewHolderNull(View v) {
            super(v);
            layout = (LinearLayout) v.findViewById(R.id.list_item_null);
            imageView = (ImageView) v.findViewById(R.id.list_item_image_null);
            textView = (TextView) v.findViewById(R.id.list_item_text_null);
        }
    }

}
