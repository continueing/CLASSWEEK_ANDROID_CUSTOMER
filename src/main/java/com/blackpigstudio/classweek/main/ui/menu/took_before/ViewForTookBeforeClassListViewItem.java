package com.blackpigstudio.classweek.main.ui.menu.took_before;

import android.content.Context;
import android.view.View;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ViewForTookBeforeClassListViewItem extends AbstractViewForListViewItem{
    public ViewForTookBeforeClassListViewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_took_before_class, this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {

    }

    public static interface ITookBeforeClass extends IListViewItem
    {

    }
}
