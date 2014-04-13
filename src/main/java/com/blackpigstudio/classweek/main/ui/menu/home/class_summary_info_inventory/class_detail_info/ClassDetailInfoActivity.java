package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking.BookingActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.inquiry.InquiryActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.order_confirmation.OrderConfirmationActivity;

public class ClassDetailInfoActivity extends ActionBarActivity implements ViewForClassDetailInfoActivity.OnInquiryChooseListener, ViewForClassDetailInfoActivity.OnBookingChooseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewForClassDetailInfoActivity view = new ViewForClassDetailInfoActivity(getApplicationContext(), this, this);
//        setTitle();
        setContentView(view.getRoot());
    }

    @Override
    public void onInquiryChoose() {
        Intent intent = new Intent(this, InquiryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBookingChoose() {
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }
}
