package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractArrayAdapter;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ArrayAdapterForReviewListView extends AbstractArrayAdapter<ViewForReviewItem.IReviewItem> {

    public ArrayAdapterForReviewListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForReviewItem(getContext());
    }
}
