package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 14..
 */
public class ViewForBookingActivity extends AbstractViewForActivity {
    public ViewForBookingActivity(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_booking,null);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvent() {

    }
}
