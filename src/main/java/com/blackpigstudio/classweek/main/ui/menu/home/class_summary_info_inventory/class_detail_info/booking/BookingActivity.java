package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.Schedule;

import java.util.ArrayList;

public class BookingActivity extends Activity implements ViewForBookingActivity.OnClassTypeSelectionRequestListener, ViewForBookingActivity.OnScheduleSelectionRequestListener {
    public static final String BUNDLE_PARM_ONE_DAY_SCHEDULES = "ONE_DAY_SCHEDULES";
    public static final String BUNDLE_PARM_ONE_MONTH_SCHEDULES = "ONE_MONTH_SCHEDULES";
    private ArrayList<Schedule> dayScheduleArrayList;
    private ArrayList<Schedule> monthScheduleArrayList;
    private ViewForBookingActivity view;
    private ClassTypeSelectionDialog classTypeSelectionDialog;
    private ScheduleSelectionDialog scheduleSelectionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        dayScheduleArrayList = (ArrayList<Schedule>)intent.getSerializableExtra(BUNDLE_PARM_ONE_DAY_SCHEDULES);
        monthScheduleArrayList = (ArrayList<Schedule>)intent.getSerializableExtra(BUNDLE_PARM_ONE_MONTH_SCHEDULES);
        classTypeSelectionDialog = new ClassTypeSelectionDialog(this);
        scheduleSelectionDialog = new ScheduleSelectionDialog(this,dayScheduleArrayList);
        view = new ViewForBookingActivity(getApplicationContext(),this,this);
        setContentView(view.getRoot());
    }

    @Override
    public void onClassTypeSelectionRequested() {
        classTypeSelectionDialog.show();
    }

    @Override
    public void onScheduleSelectionRequested() {
        scheduleSelectionDialog.show();
    }
}
