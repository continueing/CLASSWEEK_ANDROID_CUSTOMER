package com.blackpigstudio.classweek.main.module.classsummaryinfolistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.blackpigstudio.classweek.main.module.listview.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ArrayAdapterForClassSummaryInfoListView extends AbstractArrayAdapter<ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem> {

    public ArrayAdapterForClassSummaryInfoListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForClassSummaryInfoListViewItem(getContext());
    }

}
