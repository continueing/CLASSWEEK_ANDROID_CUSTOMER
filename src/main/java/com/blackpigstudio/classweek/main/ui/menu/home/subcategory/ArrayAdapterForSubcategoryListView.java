package com.blackpigstudio.classweek.main.ui.menu.home.subcategory;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;

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
