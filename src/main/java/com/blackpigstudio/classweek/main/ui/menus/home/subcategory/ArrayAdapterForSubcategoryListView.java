package com.blackpigstudio.classweek.main.ui.menus.home.subcategory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.blackpigstudio.classweek.main.module.listview.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class ArrayAdapterForSubcategoryListView extends AbstractArrayAdapter<ViewForSubcategoryListViewItem.ISubcategory> {
    public ArrayAdapterForSubcategoryListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForSubcategoryListViewItem(getContext());
    }

}
