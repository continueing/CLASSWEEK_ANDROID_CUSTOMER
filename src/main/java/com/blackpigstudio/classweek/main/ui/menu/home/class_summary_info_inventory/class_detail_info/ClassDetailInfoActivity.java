package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.Schedule;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking.BookingActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.inquiry.InquiryActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.order_confirmation.OrderConfirmationActivity;

import java.util.ArrayList;

public class ClassDetailInfoActivity extends ActionBarActivity implements ViewForClassDetailInfoActivity.OnInquiryChooseListener, ViewForClassDetailInfoActivity.OnBookingChooseListener {
    public static final String BUNDLE_PARM_URL = "URL";
    private String urlToQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        urlToQuery = intent.getStringExtra(BUNDLE_PARM_URL);
        ViewForClassDetailInfoActivity view = new ViewForClassDetailInfoActivity(getApplicationContext(), this, this);
        setContentView(view.getRoot());
    }

    @Override
    public void onInquiryChoose() {
        Intent intent = new Intent(this, InquiryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBookingChoose() {
        Intent intent = new Intent(this, BookingActivity.class);
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Schedule(Schedule.DAY_SCHEDULE_TYPE,10000,1,"월",4,20,22,0,23,0));
        schedules.add(new Schedule(Schedule.DAY_SCHEDULE_TYPE,10000,1,"수",4,23,21,0,22,0));
        schedules.add(new Schedule(Schedule.DAY_SCHEDULE_TYPE,10000,1,"월",4,27,22,0,23,0));
        intent.putExtra(BookingActivity.BUNDLE_PARM_ONE_DAY_SCHEDULES,schedules);
        schedules = new ArrayList<Schedule>();
        schedules.add(new Schedule(Schedule.MONTH_SCHEDULE_TYPE,70000,8,"월",4,20,22,0,23,0));
        schedules.add(new Schedule(Schedule.MONTH_SCHEDULE_TYPE,70000,8,"수",4,23,22,0,23,0));
        intent.putExtra(BookingActivity.BUNDLE_PARM_ONE_DAY_SCHEDULES,schedules);
        startActivity(intent);
    }
}
