package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.inquiry;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.blackpigstudio.classweek.R;

public class InquiryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewForInquiryActivity view = new ViewForInquiryActivity(getApplicationContext());
        setContentView(view.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inquiry,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
