package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassInfo;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.domain.Schedule;

public class OrderConfirmationActivity extends ActionBarActivity implements ViewForOrderConfirmationActivity.IController {

    public static final String BUNDLE_PARM_CLASS_INFO = "CLASS_INFO";
    public static final String BUNDLE_PARM_SELECTED_SCHEDULES = "SELECTED_SCHEDULES";
    private ClassInfo classInfo;
    private Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        classInfo = (ClassInfo)intent.getSerializableExtra(BUNDLE_PARM_CLASS_INFO);
        schedule = (Schedule)intent.getSerializableExtra(BUNDLE_PARM_SELECTED_SCHEDULES);
        ViewForOrderConfirmationActivity view = new ViewForOrderConfirmationActivity(getApplicationContext(),this);
        setContentView(view.getRoot());
        view.setData(classInfo.getTitle(),classInfo.getTime()+"("+classInfo.getDuration()+")",schedule.getStartDateTime(), schedule.getEndDateTime(),classInfo.getOneMonthPrice()+"원",classInfo.getFrontImageUrl());
    }


    @Override
    public void onPaymentRequested() {
        //first update this data to server if the user data is not saved
        Intent intent = new Intent();
        // TODO:
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
