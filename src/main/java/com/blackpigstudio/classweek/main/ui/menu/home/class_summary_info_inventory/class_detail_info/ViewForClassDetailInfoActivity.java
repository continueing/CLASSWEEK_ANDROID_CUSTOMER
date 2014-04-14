package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class ViewForClassDetailInfoActivity extends AbstractViewForActivity {
    private Button bt_inquiry;
    private Button bt_booking;
    private OnInquiryChooseListener onInquiryChooseListener;
    private OnBookingChooseListener onBookingChooseListener;

    public ViewForClassDetailInfoActivity(Context context, OnInquiryChooseListener anOnInquiryChooseListener, OnBookingChooseListener anOnBookingChooseListener) {
        super(context);
        this.onInquiryChooseListener = anOnInquiryChooseListener;
        this.onBookingChooseListener = anOnBookingChooseListener;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_class_detail_info,null);
    }

    @Override
    protected void initViews() {
        bt_inquiry = (Button) findViewById(R.id.bt_detail_info_inquiry);
        bt_booking = (Button) findViewById(R.id.bt_detail_info_booking);
    }

    @Override
    protected void setEvent() {
        bt_inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInquiryChooseListener.onInquiryChoose();
            }
        });

        bt_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookingChooseListener.onBookingChoose();
            }
        });


    }

    public static interface OnInquiryChooseListener
    {
        public void onInquiryChoose();
    }

    public static interface OnBookingChooseListener
    {
        public void onBookingChoose();
    }

}
