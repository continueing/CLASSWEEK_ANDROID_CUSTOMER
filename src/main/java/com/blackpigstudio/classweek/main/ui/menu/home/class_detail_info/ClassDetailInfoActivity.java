package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.InicisPaymentInfo;
import com.blackpigstudio.classweek.main.domain.class_info.ClassInfo;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking.BookingActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.inquiry.InquiryActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation.OrderConfirmationActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.payment.PaymentWebViewActivity;
import com.blackpigstudio.classweek.main.ui.singn_in_up.SignInAndUpActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassDetailInfoActivity extends ActionBarActivity implements ViewForClassDetailInfoActivity.IController, HttpRequester.NetworkResponseListener {
    public static final String SCREEN_NAME = "classDetailInfo";
    public static final int REQUEST_CODE_SELECT_SCHEDULE = 0;
    public static final int REQUEST_CODE_ORDER_CONFIRM = 1;
    public static final String BUNDLE_PARM_CLASS_ID = "classes_id";
    public static final String BUNDLE_PARM_SCHEDULE_ID = "schedule_id";
    public static final String BUNDLE_PARM_SELECTED_SCHEDULE= "SELECTED_SCHEDULES";
    public static final String BUNDLE_PARM_INICIS_PAYMENT_INFO= "INICIS_PAYMENT_INFO";

    ViewForClassDetailInfoActivity view;
    private int classId;
    private int scheduleId;
    private ClassInfo classInfo = null;
    private InicisPaymentInfo inicisPaymentInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(android.R.color.transparent);
        Intent intent = getIntent();
        classId = intent.getIntExtra(BUNDLE_PARM_CLASS_ID, -1);
        scheduleId = intent.getIntExtra(BUNDLE_PARM_SCHEDULE_ID, -1);
        view = new ViewForClassDetailInfoActivity(getApplicationContext(), this);
        requestClassDetailInfoFromServer();
        setContentView(view.getRoot());
    }

    public void requestClassDetailInfoFromServer()
    {
        ClassRequest classRequest = new ClassRequest(getApplicationContext());
        try {
            classRequest.getClassDetail(this.classId,this.scheduleId,this);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getClassDetail fail :"+ e.toString());
        }
    }

    @Override
    public void onInquiryChoose() {
        UserPreference userPreference = new UserPreference(getApplicationContext());
        if(userPreference.isLoggedIn())
        {
            Intent intent = new Intent(this, InquiryActivity.class);
            intent.putExtra(InquiryActivity.BUNDLE_PARM_CLASS_ID, this.classId);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, SignInAndUpActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBookingChoose() {
        UserPreference userPreference = new UserPreference(getApplicationContext());
        if(userPreference.isLoggedIn()) {
            Intent intent = new Intent(this, BookingActivity.class);
            intent.putExtra(BookingActivity.BUNDLE_PARM_CLASS_INFO, classInfo);
            startActivityForResult(intent, REQUEST_CODE_SELECT_SCHEDULE);
        }
        else
        {
            Intent intent = new Intent(this, SignInAndUpActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onLocationSearchRequested(String aLocation) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=" + aLocation));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_SELECT_SCHEDULE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Intent intent = new Intent(this, OrderConfirmationActivity.class);
                intent.putExtra(OrderConfirmationActivity.BUNDLE_PARM_CLASS_INFO, classInfo);
                intent.putExtra(OrderConfirmationActivity.BUNDLE_PARM_SELECTED_SCHEDULES, data.getSerializableExtra(BUNDLE_PARM_SELECTED_SCHEDULE));
                inicisPaymentInfo = (InicisPaymentInfo)data.getSerializableExtra(BUNDLE_PARM_INICIS_PAYMENT_INFO);
                startActivityForResult(intent, REQUEST_CODE_ORDER_CONFIRM);
            }
        }
        else if(requestCode == REQUEST_CODE_ORDER_CONFIRM)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Intent intent = new Intent(this, PaymentWebViewActivity.class);
                intent.putExtra(PaymentWebViewActivity.BUNDLE_PARM_INICIS_PAYMENT_INFO, inicisPaymentInfo );
                startActivity(intent);
            }
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

        try {
            classInfo = new ClassInfo(jsonClassInfoObject);
        } catch (JSONException e) {
            AppTerminator.error(this, "ClassInfo.new: " + e.toString());
        }
        view.setData(classInfo.getClassDetailInfo(),classInfo.getClassSummaryInfo(),classInfo.getFacilityInfo());
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            AppTerminator.finishActivityWithToast(getResources().getString(R.string.network_check_alert),this);
        }
        else
            AppTerminator.error(this, "classRequest.getClassDetail fail : " + errorCode);
    }

    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME+"/"+classId+"/"+scheduleId);
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
