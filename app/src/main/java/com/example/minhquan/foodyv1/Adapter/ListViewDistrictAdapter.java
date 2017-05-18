package com.example.minhquan.foodyv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minhquan.foodyv1.Object.District;
import com.example.minhquan.foodyv1.Object.Street;
import com.example.minhquan.foodyv1.R;

import java.util.List;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class ListViewDistrictAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<District> districtList;
    private int selectedGroupIndex;
    private int selectedChildIndex;

    public ListViewDistrictAdapter(Context c, List<District> districtList){
        mContext =c;
        this.districtList = districtList;
        selectedChildIndex = -1;
        selectedGroupIndex = -1;
    }

    public void setSelectedGroupIndex(int selectedGroupIndex) {
        this.selectedGroupIndex = selectedGroupIndex;
        selectedChildIndex = -1;
        notifyDataSetChanged();
    }

    public void setSelectedChildIndex(int selectedGroupIndex, int selectedChildIndex) {
        this.selectedGroupIndex = selectedGroupIndex;
        this.selectedChildIndex = selectedChildIndex;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return districtList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return districtList.get(groupPosition).getStreetList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return districtList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return districtList.get(groupPosition).getStreetList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return ((District) getGroup(groupPosition)).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return ((Street) getChild(groupPosition,childPosition)).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        District districtToDisplay = districtList.get(groupPosition);

        View listItem;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            listItem = inflater.inflate(R.layout.layout_item_district, parent, false);
        } else {
            listItem = convertView;
        }

        TextView textViewDistrictName = (TextView) listItem.findViewById(R.id.item_text_cname);
        //textViewDistrictName.setVisibility(View.GONE);
        TextView textViewNumberOfStreet = (TextView) listItem.findViewById(R.id.item_text_numberOfStreet);
        LinearLayout buttonStreet = (LinearLayout) listItem.findViewById(R.id.item_button_street);

        textViewDistrictName.setText(districtToDisplay.getName());

        String strToDisplay = String.valueOf(districtToDisplay.getStreetList().size())+" đường";
        textViewNumberOfStreet.setText(strToDisplay);

        // selected ?
        if (groupPosition == selectedGroupIndex){
            if (selectedChildIndex == -1) {
                // chon quan huyen, ko chon duong
                //((ExpandableListView) parent).collapseGroup(groupPosition);
                textViewDistrictName.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            }
            else {
                // co chon duong
               // ((ExpandableListView) parent).expandGroup(groupPosition);
                textViewDistrictName.setTextColor(mContext.getResources().getColor(R.color.textColorMain));
            }
        }
        else {
            // ko phai quan/huyen dang chon
            //((ExpandableListView) parent).collapseGroup(groupPosition);
            textViewDistrictName.setTextColor(mContext.getResources().getColor(R.color.textColorMain));
        }

        // mo rong khi nhan nut ben phai
        // TODO: BUG khong expand
        final boolean fisExpanded = isExpanded;
        final ViewGroup fparent = parent;
        final int fgroupPosition = groupPosition;
        buttonStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fisExpanded)
                    ((ExpandableListView) fparent).collapseGroup(fgroupPosition);
                else
                    ((ExpandableListView) fparent).expandGroup(fgroupPosition, true);
            }
        });

        return listItem;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Street streetToDisplay = districtList.get(groupPosition).getStreetList().get(childPosition);

        View listItem;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            listItem = inflater.inflate(R.layout.layout_item_text_only_margin_left,  parent, false);
        } else {
            listItem = convertView;
        }

        TextView textView = (TextView) listItem.findViewById(R.id.item_text);
        textView.setText(streetToDisplay.getName());

        // selected ?
        if (groupPosition == selectedGroupIndex && childPosition == selectedChildIndex){
            textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }
        else {
            textView.setTextColor(mContext.getResources().getColor(R.color.textColorMain));
        }

        return listItem;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
