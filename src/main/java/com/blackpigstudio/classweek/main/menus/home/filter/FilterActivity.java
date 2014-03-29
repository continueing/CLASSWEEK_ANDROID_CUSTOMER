package com.blackpigstudio.classweek.main.menus.home.filter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class FilterActivity extends ActionBarActivity implements FilterActivityLayout.OnSubmitButtonClickListener, FilterActivityLayout.OnLocationSettingDialogPopupRequestListener {
    public static final int REQUEST_CODE_GET_QUERY = 0;
    public static final String INTENT_PARM_QUERY = "query";
    private FilterActivityLayout filterActivityLayout;
    private LocationSettingDialog locationSettingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterActivityLayout = new FilterActivityLayout(this);
        setContentView(filterActivityLayout);
        locationSettingDialog = new LocationSettingDialog(this, filterActivityLayout);
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
