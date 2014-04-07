package com.blackpigstudio.classweek.main.ui.menus.home.subcategory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class ArrayAdapterForSubcategoryListView extends ArrayAdapter<ViewForSubcategoryListViewItem.ISubcategory> {
    public ArrayAdapterForSubcategoryListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return instantiateClassSummaryView(position,convertView);
    }

    private View instantiateClassSummaryView(int position, View convertView)
    {
        ViewForSubcategoryListViewItem subcategoryView = (ViewForSubcategoryListViewItem) convertView;

        if (subcategoryView == null)
            subcategoryView = new ViewForSubcategoryListViewItem(getContext());

        subcategoryView.setData(getItem(position));
        return subcategoryView;
    }

}
