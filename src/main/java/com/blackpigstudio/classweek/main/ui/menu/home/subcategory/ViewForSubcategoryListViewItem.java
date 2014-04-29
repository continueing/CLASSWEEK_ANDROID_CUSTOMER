package com.blackpigstudio.classweek.main.ui.menu.home.subcategory;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class ViewForSubcategoryListViewItem extends AbstractViewForListViewItem {

    private ISubcategory iSubcategory;

    private TextView tv_subcategory_title;
    private TextView tv_description;
    private SmartImageView siv_subcategory_front;

    private RelativeLayout rl_dim;

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
        tv_description = (TextView) findViewById(R.id.tv_subcategory_description);
        siv_subcategory_front = (SmartImageView) findViewById_(R.id.siv_subcategory_front);
        rl_dim = (RelativeLayout) findViewById(R.id.rl_subcategory_dim);
    }


    @Override
    protected void setEvents() {



    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        this.iSubcategory = (ISubcategory)aIListViewItem;
        tv_subcategory_title.setText(iSubcategory.getKorTitle());
        tv_description.setText(iSubcategory.getDescription());
        siv_subcategory_front.setImageUrl(iSubcategory.getFrontImageUrl());
    }

    public static interface ISubcategory extends IListViewItem
    {
        public String getKorTitle();
        public String getFrontImageUrl();
        public String getEngTitle();
        public String getDescription();
    }
}
