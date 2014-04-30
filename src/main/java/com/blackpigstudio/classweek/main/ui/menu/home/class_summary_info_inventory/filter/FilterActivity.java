package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class FilterActivity extends ActionBarActivity implements ViewForFilterActivity.OnSubmitButtonClickListener, ViewForFilterActivity.OnLocationSettingDialogPopupRequestListener, LocationSettingDialog.ILocationReceiver {


    public static final String BUNDLE_PARM_OF_URL_LOCATION_KEY = "LOCATION_KEY";
    public static final String BUNDLE_PARM_OF_URL_WEEK_DAY_KEY = "WEEK_DAY_KEY";
    public static final String BUNDLE_PARM_OF_URL_TIME_KEY = "TIME_KEY";
    public static final String BUNDLE_PARM_OF_URL_PRICE_KEY = "PRICE_KEY";

    private ViewForFilterActivity viewForFilterActivity;
    private LocationSettingDialog locationSettingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(android.R.color.transparent);
        viewForFilterActivity = new ViewForFilterActivity(this,this,this);
        setContentView(viewForFilterActivity.getRoot());
        locationSettingDialog = new LocationSettingDialog(this, this);
    }

    @Override
    public void onSearchConditionDelivered(String aLocation, boolean mon, boolean tue, boolean wen, boolean tur, boolean fri, boolean sat, boolean sun, boolean morning, boolean afternoon, boolean evening, int aPrice) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PARM_OF_URL_LOCATION_KEY, aLocation);

        String weekDay = "";

        if(mon)
            weekDay+="0";
        if(tue)
            weekDay+="1";
        if(wen)
            weekDay+="2";
        if(tur)
            weekDay+="3";
        if(fri)
            weekDay+="4";
        if(sat)
            weekDay+="5";
        if(sun)
            weekDay+="6";

        intent.putExtra(BUNDLE_PARM_OF_URL_WEEK_DAY_KEY, weekDay);

        String time = "";

        if(morning)
            time += "0";
        if(afternoon)
            time += "1";
        if(evening)
            time += "2";

        intent.putExtra(BUNDLE_PARM_OF_URL_TIME_KEY, time);

        intent.putExtra(BUNDLE_PARM_OF_URL_PRICE_KEY, String.valueOf(aPrice));

        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void onPopupRequestDelivered() {
        locationSettingDialog.show();
    }

    @Override
    public void setLocation(String aLocation) {
        if(!"강남".equals(aLocation))
        {
            Toast.makeText(getApplicationContext(),"죄송합니다. 아직 " + aLocation + " 지역은 준비 단계에 있습니다.",Toast.LENGTH_LONG).show();
        }
        else
        {
            viewForFilterActivity.setLocation(aLocation);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }
}
