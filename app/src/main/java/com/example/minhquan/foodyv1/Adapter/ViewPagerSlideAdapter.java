package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.minhquan.foodyv1.R;

/**
 * Created by MinhQuan on 4/18/2017.
 */

public class ViewPagerSlideAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int[] drawableList;

    public ViewPagerSlideAdapter(Context context, int[] drawableList) {
        mContext = context;
        this.drawableList = drawableList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return drawableList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_slide_item ,container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.slide_item);
        imageView.setImageResource(drawableList[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
