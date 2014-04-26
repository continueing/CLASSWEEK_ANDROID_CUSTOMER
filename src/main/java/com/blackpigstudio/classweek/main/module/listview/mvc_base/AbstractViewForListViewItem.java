package com.blackpigstudio.classweek.main.module.listview.mvc_base;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by continueing on 2014. 4. 7..
 */
public abstract class AbstractViewForListViewItem extends LinearLayout {
    private View root;

    public AbstractViewForListViewItem(Context context) {
        super(context);
        root = inflate();
        initViews();
        setEvents();
    }

    protected abstract View inflate();
    protected abstract void initViews();
    protected abstract void setEvents();
    protected abstract void setData(IListViewItem aIListViewItem);

    protected View findViewById_(int aResourceId)
    {
        return root.findViewById(aResourceId);
    }

}
