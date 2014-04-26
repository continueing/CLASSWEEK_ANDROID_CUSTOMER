package com.blackpigstudio.classweek.main.ui.navigation_drawer_menu;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 4. 26..
 */
public class ArrayAdapterForNavigationDrawerMenuListView extends AbstractArrayAdapter<ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem> {

    public ArrayAdapterForNavigationDrawerMenuListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForNavigationDrawerMenuListViewItem(getContext());
    }
}
