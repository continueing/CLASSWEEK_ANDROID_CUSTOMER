package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class FilterActivity extends ActionBarActivity implements ViewForFilterActivity.OnSubmitButtonClickListener, ViewForFilterActivity.OnLocationSettingDialogPopupRequestListener {
    public static final int REQUEST_CODE_GET_QUERY = 0;
    public static final String INTENT_PARM_QUERY = "query";
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
    public void onSearchQueryDelivered(String aQuery) {
        Intent intent = new Intent();
        intent.putExtra(INTENT_PARM_QUERY,aQuery);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void onPopupRequestDelivered() {
        locationSettingDialog.show();
    }
}
