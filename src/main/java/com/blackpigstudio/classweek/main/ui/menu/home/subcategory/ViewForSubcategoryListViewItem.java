package com.blackpigstudio.classweek.main.ui.menu.home.subcategory;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.IListViewItem;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class ViewForSubcategoryListViewItem extends AbstractViewForListViewItem {

    private ISubcategory iSubcategory;

    private TextView tv_subcategory_title;
    private SmartImageView siv_subcategory_front;


    public ViewForSubcategoryListViewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_subcategory, this);
    }

    @Override
    protected void initViews() {
        tv_subcategory_title = (TextView) findViewById_(R.id.tv_subcategory_title);
        siv_subcategory_front = (SmartImageView) findViewById_(R.id.siv_subcategory_front);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        this.iSubcategory = (ISubcategory)aIListViewItem;
        tv_subcategory_title.setText(iSubcategory.getTitle());
        siv_subcategory_front.setImageUrl(iSubcategory.getFrontImageUrl());
    }

    public static interface ISubcategory extends IListViewItem
    {
        public String getTitle();
        public String getFrontImageUrl();
        public String getSubCategoryName();
        public String getDescription();
    }
}
