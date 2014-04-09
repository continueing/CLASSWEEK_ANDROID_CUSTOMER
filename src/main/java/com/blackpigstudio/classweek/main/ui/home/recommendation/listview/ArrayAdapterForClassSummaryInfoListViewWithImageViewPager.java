package com.blackpigstudio.classweek.main.ui.home.recommendation.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.ArrayAdapterForClassSummaryInfoListView;

/**
 * Created by continueing on 2014. 4. 1..
 */
public class ArrayAdapterForClassSummaryInfoListViewWithImageViewPager extends ArrayAdapterForClassSummaryInfoListView {
    private ViewForViewPagerInListView viewForViewPagerInListView;

    public ArrayAdapterForClassSummaryInfoListViewWithImageViewPager(Context context, int resource) {
        super(context, resource);
        this.viewForViewPagerInListView = new ViewForViewPagerInListView(getContext());
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position == 0 )
        {
            return this.viewForViewPagerInListView;
        }
        else
        {
            if( convertView instanceof ViewForViewPagerInListView)
                return super.getView(position -1, null, parent);
            else
                return super.getView(position -1, convertView, parent);
        }
    }
}
