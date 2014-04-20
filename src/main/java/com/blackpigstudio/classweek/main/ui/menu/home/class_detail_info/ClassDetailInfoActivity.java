package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.blackpigstudio.classweek.main.domain.Schedule;
import com.blackpigstudio.classweek.main.domain.class_info.ClassInfo;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking.BookingActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.inquiry.InquiryActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation.OrderConfirmationActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.payment.PaymentWebViewActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClassDetailInfoActivity extends ActionBarActivity implements ViewForClassDetailInfoActivity.OnInquiryChooseListener, ViewForClassDetailInfoActivity.OnBookingChooseListener, HttpRequester.NetworkResponseListener {
    public static final int REQUEST_CODE_SELECT_SCHEDULE = 0;
    public static final int REQUEST_CODE_ORDER_CONFIRM = 1;
    public static final String BUNDLE_PARM_CLASS_ID = "classes_id";
    public static final String BUNDLE_PARM_SCHEDULE_ID = "schedule_id";
    ViewForClassDetailInfoActivity view;
    private int classId;
    private int scheduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        classId = intent.getIntExtra(BUNDLE_PARM_CLASS_ID, -1);
        scheduleId = intent.getIntExtra(BUNDLE_PARM_SCHEDULE_ID, -1);
        view = new ViewForClassDetailInfoActivity(getApplicationContext(), this, this);
        requestClassDetailInfoFromServer();
        setContentView(view.getRoot());
    }

    public void requestClassDetailInfoFromServer()
    {
        ClassRequest classRequest = new ClassRequest(getApplicationContext());
        try {
            classRequest.getClassDetail(this.classId,this.scheduleId,this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInquiryChoose() {
        Intent intent = new Intent(this, InquiryActivity.class);
        intent.putExtra(InquiryActivity.BUNDLE_PARM_CLASS_ID, this.classId);
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
        startActivityForResult(intent, REQUEST_CODE_SELECT_SCHEDULE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_SELECT_SCHEDULE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                //TODO: should set parameter to be sent.
                Intent intent = new Intent(this, OrderConfirmationActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ORDER_CONFIRM);
            }
        }
        else if(requestCode == REQUEST_CODE_ORDER_CONFIRM)
        {
            Intent intent = new Intent(this, PaymentWebViewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        JSONObject jsonClassInfoObject = null;

        try {
            jsonClassInfoObject = jsonObject.getJSONObject(JsonResponseHandler.PARM_DATA);
        } catch (JSONException e) {
            AppTerminator.error(this, "JSONObject.getJSONObject(): " + e.toString());
        }

        ClassInfo classInfo = null;
        try {
            classInfo = new ClassInfo(jsonClassInfoObject);
        } catch (JSONException e) {
            AppTerminator.error(this, "ClassInfo.new: " + e.toString());
        }
        view.setData(classInfo.getClassDetailInfo(),classInfo.getClassSummaryInfo(),classInfo.getFacilityInfo());

        //TODO: should store schedule data
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {

    }
}
