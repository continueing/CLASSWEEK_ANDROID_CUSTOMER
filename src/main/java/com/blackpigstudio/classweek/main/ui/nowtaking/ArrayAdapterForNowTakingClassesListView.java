package com.blackpigstudio.classweek.main.ui.nowtaking;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ArrayAdapterForNowTakingClassesListView extends AbstractArrayAdapter<ViewForNowTakingClassListViewItem.INowTakingClass>{
    public ArrayAdapterForNowTakingClassesListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForNowTakingClassListViewItem(getContext());
    }
}
