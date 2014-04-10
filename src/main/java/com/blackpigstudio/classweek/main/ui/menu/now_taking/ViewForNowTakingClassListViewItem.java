package com.blackpigstudio.classweek.main.ui.menu.now_taking;

import android.content.Context;
import android.view.View;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ViewForNowTakingClassListViewItem extends AbstractViewForListViewItem {

    public ViewForNowTakingClassListViewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_now_taking_class, this);
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

    public static interface INowTakingClass extends IListViewItem
    {

    }
}
