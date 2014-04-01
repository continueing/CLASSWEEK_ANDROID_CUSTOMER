package com.blackpigstudio.classweek.main.module.classsummaryinfolistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ArrayAdapterForClassSummaryInfoListView extends ArrayAdapter<ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem> {

    public ArrayAdapterForClassSummaryInfoListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return instantiateClassSummaryView(position,convertView);
    }

    private View instantiateClassSummaryView(int position, View convertView)
    {
        ViewForClassSummaryInfoListViewItem classSummaryInfoView = (ViewForClassSummaryInfoListViewItem) convertView;

        if (classSummaryInfoView == null)
            classSummaryInfoView = new ViewForClassSummaryInfoListViewItem(getContext());

        classSummaryInfoView.setData(getItem(position));
        return classSummaryInfoView;
    }


}
