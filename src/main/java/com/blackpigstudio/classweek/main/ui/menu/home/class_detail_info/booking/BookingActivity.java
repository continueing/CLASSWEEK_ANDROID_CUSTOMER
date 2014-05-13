package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.InicisPaymentInfo;
import com.blackpigstudio.classweek.main.domain.Schedule;
import com.blackpigstudio.classweek.main.domain.class_info.ClassInfo;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingActivity extends Activity implements ViewForBookingActivity.IViewListener, ClassTypeSelectionDialog.OnClassTypeSelectionListener, ScheduleSelectionDialog.OnScheduleSelectionListener, HttpRequester.NetworkResponseListener {
    public static final String SCREEN_NAME = "booking";
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
        ClassRequest classRequest = new ClassRequest(getApplicationContext());
        try {
            classRequest.getPaymentInfo(classInfo.getClassId()+"", classInfo.getScheduleId()+"",selectedSchedule.getStartDateTimeForPayment(),selectedSchedule.getEndDateTimeForPayment(),this);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getPaymentInfo fail :" + e.toString());
        }
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



    @Override
    public void onSuccess(JSONObject jsonObject) {
        view.releaseSubmitButton();
        JSONObject jsonPaymentInfoObject = null;

        try {
            jsonPaymentInfoObject = jsonObject.getJSONObject(JsonResponseHandler.PARM_DATA);
        } catch (JSONException e) {
            AppTerminator.error(this, "JSONObject.getJSONObject(): " + e.toString());
        }

        InicisPaymentInfo inicisPaymentInfo = null;
        try {
            inicisPaymentInfo = new InicisPaymentInfo(jsonPaymentInfoObject);
        } catch (JSONException e) {
            AppTerminator.error(this, "InicisPaymentInfo.new: " + e.toString());
        }

        Intent intent = new Intent();
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_SELECTED_SCHEDULE, this.selectedSchedule);
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_INICIS_PAYMENT_INFO, inicisPaymentInfo);
        setResult(Activity.RESULT_OK, intent);
        finish();


    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        view.releaseSubmitButton();
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            Toast.makeText(this, getResources().getString(R.string.network_check_alert), Toast.LENGTH_LONG).show();
        }
        else
        {
            AppTerminator.error(this, "userRequest.update fail : " + errorCode);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME+"/"+classInfo.getClassId()+"/"+classInfo.getScheduleId());
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

}
