package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.blackpigstudio.classweek.main.domain.Schedule;

import java.util.ArrayList;

public class BookingActivity extends Activity implements ViewForBookingActivity.IViewListener, ClassTypeSelectionDialog.OnClassTypeSelectionListener, ScheduleSelectionDialog.OnScheduleSelectionListener {
    public static final String BUNDLE_PARM_ONE_MONTH_SCHEDULES = "ONE_MONTH_SCHEDULES";

    private ViewForBookingActivity view;
    private ClassTypeSelectionDialog classTypeSelectionDialog;
    private ScheduleSelectionDialog scheduleSelectionDialog;
    private boolean isScheduleDialogReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        classTypeSelectionDialog = new ClassTypeSelectionDialog(this,this);
        scheduleSelectionDialog = new ScheduleSelectionDialog(this, this);
        scheduleSelectionDialog.setSchedules((ArrayList<Schedule>)intent.getSerializableExtra(BUNDLE_PARM_ONE_MONTH_SCHEDULES));
        isScheduleDialogReady = false;
        view = new ViewForBookingActivity(getApplicationContext(),this);
        setContentView(view.getRoot());
    }

    @Override
    public void onClassTypeSelectionRequested() {
        classTypeSelectionDialog.show();
    }

    @Override
    public void onPaymentRequested() {
        // finish and activity result should be delivered
        finish();
    }

    @Override
    public void onScheduleSelectionRequested() {
        if(isScheduleDialogReady == true) {
            scheduleSelectionDialog.show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"클래스 타입을 선택하지 않으셨습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onOneDaySelected() {
        // 경고 아직 지원 안됨
        Toast.makeText(getApplicationContext(),"아직 1일 클래스는 지원되지 않습니다.",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOneMonthSelected() {
       isScheduleDialogReady = true;
        // Button Test 변경해야함, 이 함수의 parameter로 String 받으면 좋을 듯
    }

    @Override
    public void onScheduleSelected(Schedule aSchedule) {
        Toast.makeText(getApplicationContext(),aSchedule.toString(),Toast.LENGTH_SHORT).show();
        view.enablePayment();
    }
}
