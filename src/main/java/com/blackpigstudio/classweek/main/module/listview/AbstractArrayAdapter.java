package com.blackpigstudio.classweek.main.module.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by continueing on 2014. 4. 7..
 *
 */
abstract public class AbstractArrayAdapter<T extends IListViewItem> extends ArrayAdapter<T>{

    public AbstractArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbstractViewForListViewItem abstractViewForListViewItem = (AbstractViewForListViewItem) convertView;

        if (abstractViewForListViewItem == null)
            abstractViewForListViewItem = getInstance();

        abstractViewForListViewItem.setData(getItem(position));
        return abstractViewForListViewItem;
    }

    public abstract AbstractViewForListViewItem getInstance();
}
