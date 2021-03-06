package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassInfo;
import com.blackpigstudio.classweek.main.domain.Schedule;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.etc.EventOfGoogleAnalytics;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.network.UserRequest;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderConfirmationActivity extends ActionBarActivity implements ViewForOrderConfirmationActivity.IController, HttpRequester.NetworkResponseListener {
    private ViewForOrderConfirmationActivity view;
    public static final String SCREEN_NAME = "orderConfirmation";
    public static final String BUNDLE_PARM_CLASS_INFO = "CLASS_INFO";
    public static final String BUNDLE_PARM_SELECTED_SCHEDULES = "SELECTED_SCHEDULES";
    UserPreference userPreference;
    private ClassInfo classInfo;
    private Schedule schedule;
    private EasyTracker easyTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(android.R.color.transparent);
        Intent intent = getIntent();
        classInfo = (ClassInfo)intent.getSerializableExtra(BUNDLE_PARM_CLASS_INFO);
        schedule = (Schedule)intent.getSerializableExtra(BUNDLE_PARM_SELECTED_SCHEDULES);
        view = new ViewForOrderConfirmationActivity(getApplicationContext(),this);
        setContentView(view.getRoot());
        userPreference = new UserPreference(getApplicationContext());
        view.setData(classInfo.getTitle(),classInfo.getTime()+"("+classInfo.getDuration()+")",schedule.getStartDateTime(), schedule.getEndDateTime(),classInfo.getOneMonthDiscountedPrice()+"원",classInfo.getFrontImageUrl(),userPreference.getName(), userPreference.getBirth(), userPreference.getPhoneNumber(), userPreference.getSex());
    }

    @Override
    public void onPaymentRequested() {
        easyTracker.send(
                MapBuilder.createEvent(
                        EventOfGoogleAnalytics.CATEGORY_ACQUISITION,
                        EventOfGoogleAnalytics.ACTION_TRY_PAYMENT,
                        ""+classInfo.getClassId(),
                        (long)classInfo.getScheduleId()
                ).build()
        );

        UserRequest request = new UserRequest(getApplicationContext());

        String birthDate = makeBirthDateWithHyphen(view.getBirthDate());
        String phoneNumber = makePhoneNumberWithHyphen(view.getPhoneNumber());

        view.getPhoneNumber();
        try {
            if(view.getSex() == R.id.rb_order_confirmation_male)
                request.update(view.getName(),birthDate, phoneNumber, "M", this);
            else
            request.update(view.getName(),birthDate, phoneNumber, "W", this);

        } catch (JSONException e) {
            AppTerminator.error(this, "ClassRequest.inquire : " + e.toString());
        }
    }

    private String makeBirthDateWithHyphen(String aBirthDateWithoutHyphen)
    {
        StringBuffer birthDateStringBuffer = new StringBuffer();
        birthDateStringBuffer.append(aBirthDateWithoutHyphen);
        birthDateStringBuffer.insert(4, '-');
        birthDateStringBuffer.insert(7, '-');
        return birthDateStringBuffer.toString();
    }

    private String makePhoneNumberWithHyphen(String aPhoneNumberWithoutHyphen)
    {
        StringBuffer phoneNumberStringBuffer = new StringBuffer();
        phoneNumberStringBuffer.append(aPhoneNumberWithoutHyphen);
        phoneNumberStringBuffer.insert(3,'-');
        if(aPhoneNumberWithoutHyphen.length()== 11) // ex. 010-4527-9272
        {
            phoneNumberStringBuffer.insert(8,'-');
        }
        else if(aPhoneNumberWithoutHyphen.length()== 10) // ex. 010-452-9272
        {
            phoneNumberStringBuffer.insert(7,'-');
        }
        return phoneNumberStringBuffer.toString();
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        userPreference.setName(view.getName());
        userPreference.setBirthDate(view.getBirthDate());
        userPreference.setPhoneNumber(view.getPhoneNumber());
        userPreference.setSex(view.getSex());
        view.releaseSubmitButton();
        Intent intent = new Intent();
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
        easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME + "/" + classInfo.getClassId() + "/" + classInfo.getScheduleId());
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
