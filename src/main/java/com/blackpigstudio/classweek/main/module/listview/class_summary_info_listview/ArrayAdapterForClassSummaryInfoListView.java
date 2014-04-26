package com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ArrayAdapterForClassSummaryInfoListView extends AbstractArrayAdapter<IClassSummaryInfoItem> {

    public ArrayAdapterForClassSummaryInfoListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForClassSummaryInfoListViewItem(getContext());
    }

}
