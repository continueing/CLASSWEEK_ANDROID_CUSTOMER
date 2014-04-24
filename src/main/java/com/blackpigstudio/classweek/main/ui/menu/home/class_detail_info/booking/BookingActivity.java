package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.blackpigstudio.classweek.main.domain.Schedule;
import com.blackpigstudio.classweek.main.domain.class_info.ClassInfo;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;

import java.util.ArrayList;

public class BookingActivity extends Activity implements ViewForBookingActivity.IViewListener, ClassTypeSelectionDialog.OnClassTypeSelectionListener, ScheduleSelectionDialog.OnScheduleSelectionListener {
    public static final String BUNDLE_PARM_CLASS_INFO = "CLASS_INFO";

    private ViewForBookingActivity view;
    private ClassTypeSelectionDialog classTypeSelectionDialog;
    private ScheduleSelectionDialog scheduleSelectionDialog;
    private boolean isScheduleDialogReady;
    private ClassInfo classInfo;
    private Schedule selectedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        classInfo = (ClassInfo)intent.getSerializableExtra(BUNDLE_PARM_CLASS_INFO);
        classTypeSelectionDialog = new ClassTypeSelectionDialog(this,this, classInfo.getOneDayPrice(), classInfo.getOneMonthPrice(), classInfo.getNumberOfClassPerMonth());
        scheduleSelectionDialog = new ScheduleSelectionDialog(this, this);
        scheduleSelectionDialog.setSchedules(classInfo.getSchedules());
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
        Intent intent = new Intent();
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_SELECTED_SCHEDULE, this.selectedSchedule);
        setResult(Activity.RESULT_OK, intent);
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
       view.setClassTypeAndPriceTextView( classInfo.getNumberOfClassPerMonth() + "회(1개월)",classInfo.getOneMonthPrice());
    }

    @Override
    public void onScheduleSelected(Schedule aSchedule) {
        selectedSchedule = aSchedule;
        view.setScheduleTextView(aSchedule.getStartDateTime());
        view.enablePayment();
    }
}
