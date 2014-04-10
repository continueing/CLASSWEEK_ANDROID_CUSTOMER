package com.blackpigstudio.classweek.main.ui.menu.took_before;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ArrayAdapterForTookBeforeClassListView extends AbstractArrayAdapter<ViewForTookBeforeClassListViewItem.ITookBeforeClass>{
    public ArrayAdapterForTookBeforeClassListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForTookBeforeClassListViewItem(getContext());
    }
}
