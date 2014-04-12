package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class FilterActivity extends ActionBarActivity implements ViewForFilterActivity.OnSubmitButtonClickListener, ViewForFilterActivity.OnLocationSettingDialogPopupRequestListener {
    public static final int REQUEST_CODE_GET_QUERY = 0;

    public static final String BUNDLE_PARM_OF_URL_LOCATION_KEY = "LOCATION";

    public static final String BUNDLE_PARM_OF_URL_MON_KEY = "MON";
    public static final String BUNDLE_PARM_OF_URL_TUE_KEY = "TUE";
    public static final String BUNDLE_PARM_OF_URL_WEN_KEY = "WEN";
    public static final String BUNDLE_PARM_OF_URL_TUR_KEY = "TUR";
    public static final String BUNDLE_PARM_OF_URL_FRI_KEY = "FRI";
    public static final String BUNDLE_PARM_OF_URL_SAT_KEY = "SAT";
    public static final String BUNDLE_PARM_OF_URL_SUN_KEY = "SUN";

    public static final String BUNDLE_PARM_OF_URL_MORNING_KEY = "MORNING";
    public static final String BUNDLE_PARM_OF_URL_AFTERNOON_KEY = "AFTERNOON";
    public static final String BUNDLE_PARM_OF_URL_EVENING_KEY = "EVENING";

    public static final String BUNDLE_PARM_OF_URL_PRICE_KEY = "PRICE";

    private ViewForFilterActivity viewForFilterActivity;
    private LocationSettingDialog locationSettingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewForFilterActivity = new ViewForFilterActivity(this,this,this);
        setContentView(viewForFilterActivity.getRoot());
        locationSettingDialog = new LocationSettingDialog(this, viewForFilterActivity);
    }

    @Override
    public void onSearchConditionDelivered(String aLocation, boolean mon, boolean tue, boolean wen, boolean tur, boolean fri, boolean sat, boolean sun, boolean morning, boolean afternoon, boolean evening, int aPrice) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PARM_OF_URL_LOCATION_KEY, aLocation);

        intent.putExtra(BUNDLE_PARM_OF_URL_MON_KEY, mon);
        intent.putExtra(BUNDLE_PARM_OF_URL_TUE_KEY, tue);
        intent.putExtra(BUNDLE_PARM_OF_URL_WEN_KEY, wen);
        intent.putExtra(BUNDLE_PARM_OF_URL_TUR_KEY, tur);
        intent.putExtra(BUNDLE_PARM_OF_URL_FRI_KEY, fri);
        intent.putExtra(BUNDLE_PARM_OF_URL_SAT_KEY, sat);
        intent.putExtra(BUNDLE_PARM_OF_URL_SUN_KEY, sun);

        intent.putExtra(BUNDLE_PARM_OF_URL_MORNING_KEY, morning);
        intent.putExtra(BUNDLE_PARM_OF_URL_AFTERNOON_KEY, afternoon);
        intent.putExtra(BUNDLE_PARM_OF_URL_EVENING_KEY, evening);

        intent.putExtra(BUNDLE_PARM_OF_URL_PRICE_KEY, aPrice);

        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void onPopupRequestDelivered() {
        locationSettingDialog.show();
    }
}
