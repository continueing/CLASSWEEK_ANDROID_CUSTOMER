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
        schedules.add(new Schedule(1, Schedule.MONTH_SCHEDULE_TYPE, "03-19(수) 19:00","04-19(수) 19:00"));
        schedules.add(new Schedule(2, Schedule.MONTH_SCHEDULE_TYPE, "03-24(월) 19:00","04-24(월) 19:00"));
        schedules.add(new Schedule(3, Schedule.MONTH_SCHEDULE_TYPE, "03-26(수) 19:00","05-1(월) 19:00"));
        intent.putExtra(BookingActivity.BUNDLE_PARM_ONE_MONTH_SCHEDULES,schedules);
        startActivity(intent);
    }
}
