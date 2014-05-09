package com.blackpigstudio.classweek.main.ui.menu.now_taking.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ViewForNowTakingClassListViewItem extends AbstractViewForListViewItem {
    private SmartImageView siv_front;
    private TextView tv_class_title;
    private TextView tv_class_time;
    private TextView tv_class_start_date_time;
    private TextView tv_class_end_date_time;
    private TextView tv_class_current_state;


    public ViewForNowTakingClassListViewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_now_taking_class, this);
    }

    @Override
    protected void initViews() {
        siv_front = (SmartImageView) findViewById_(R.id.siv_now_taking_class_front);
        tv_class_title = (TextView)findViewById_(R.id.tv_now_taking_class_title);
        tv_class_time = (TextView)findViewById_(R.id.tv_now_taking_class_time);
        tv_class_start_date_time = (TextView)findViewById_(R.id.tv_now_taking_class_start_date_time);
        tv_class_end_date_time = (TextView)findViewById_(R.id.tv_now_taking_class_end_date_time);
        tv_class_current_state = (TextView)findViewById_(R.id.tv_now_taking_class_current_state);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        INowTakingClass iNowTakingClass = (INowTakingClass)aIListViewItem;
        siv_front.setImageUrl(iNowTakingClass.getFrontImageUrl());
        tv_class_title.setText(iNowTakingClass.getTitle());
        tv_class_time.setText(iNowTakingClass.getTime());
        tv_class_start_date_time.setText(iNowTakingClass.getStartDateTime());
        tv_class_end_date_time.setText(iNowTakingClass.getEndDateTime());
        tv_class_current_state.setText(iNowTakingClass.getCurrentState());
    }

    public static interface INowTakingClass extends IListViewItem
    {
        public String getTitle();

        public String getStartDateTime();

        public String getEndDateTime();

        public String getCurrentState();

        public String getTime();

        public String getFrontImageUrl();
    }
}
