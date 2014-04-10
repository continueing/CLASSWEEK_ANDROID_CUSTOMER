package com.blackpigstudio.classweek.main.ui.menu.now_taking;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ArrayAdapterForNowTakingClassListView extends AbstractArrayAdapter<ViewForNowTakingClassListViewItem.INowTakingClass>{
    public ArrayAdapterForNowTakingClassListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForNowTakingClassListViewItem(getContext());
    }
}
