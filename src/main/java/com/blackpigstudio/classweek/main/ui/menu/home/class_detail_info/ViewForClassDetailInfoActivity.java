package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassDetailInfo;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.domain.class_info.FacilityInfo;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class ViewForClassDetailInfoActivity extends AbstractViewForActivity {
    private ViewPager vp_class_detail_images;
    private Button bt_inquiry;
    private Button bt_booking;
    private IController iController;
    private TextView tv_title;
    private TextView tv_start_time_1;
    private TextView tv_start_time_2;
    private TextView tv_start_time_3;
    private TextView tv_one_day_price;
    private TextView tv_one_month_price;
    private TextView tv_location;
    private TextView tv_description;
    private TextView tv_prerequisite;
    private TextView tv_refund_info;
    private TextView tv_progress_type;

    private TextView tv_toilet;
    private TextView tv_fitting_room;
    private TextView tv_shower_stall;
    private TextView tv_locker;
    private TextView tv_parking_lot;
    private TextView tv_practice_room;
    private TextView tv_instrument_rental;


    public ViewForClassDetailInfoActivity(Context context, IController anIController) {
        super(context);
        this.iController = anIController;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_class_detail_info,null);
    }

    @Override
    protected void initViews() {
        vp_class_detail_images = (ViewPager) findViewById(R.id.vp_class_detail_images);
        bt_inquiry = (Button) findViewById(R.id.bt_detail_info_inquiry);
        bt_booking = (Button) findViewById(R.id.bt_detail_info_booking);
        tv_title = (TextView)findViewById(R.id.tv_class_detail_title);
        tv_start_time_1 = (TextView)findViewById(R.id.tv_class_detail_start_time_1);
        tv_start_time_2 = (TextView)findViewById(R.id.tv_class_detail_start_time_2);;
        tv_start_time_3 = (TextView)findViewById(R.id.tv_class_detail_start_time_3);;
        tv_one_day_price = (TextView)findViewById(R.id.tv_class_detail_one_day_price);
        tv_one_month_price = (TextView)findViewById(R.id.tv_class_detail_month_day_price);
        tv_location = (TextView)findViewById(R.id.tv_class_detail_location);
        tv_description = (TextView)findViewById(R.id.tv_class_detail_description);
        tv_prerequisite = (TextView)findViewById(R.id.tv_class_detail_prerequisite);
        tv_refund_info = (TextView)findViewById(R.id.tv_class_detail_refund_info);
        tv_progress_type = (TextView)findViewById(R.id.tv_class_detail_progress_type);

        tv_toilet = (TextView)findViewById(R.id.tv_class_detail_facility_toilet);
        tv_fitting_room = (TextView)findViewById(R.id.tv_class_detail_facility_fitting_room);
        tv_shower_stall = (TextView)findViewById(R.id.tv_class_detail_facility_shower_stall);
        tv_locker = (TextView)findViewById(R.id.tv_class_detail_facility_locker);
        tv_parking_lot = (TextView)findViewById(R.id.tv_class_detail_facility_parking_lot);
        tv_practice_room = (TextView)findViewById(R.id.tv_class_detail_facility_practice_room);
        tv_instrument_rental = (TextView)findViewById(R.id.tv_class_detail_facility_instrument_rental);
        
    }

    @Override
    protected void setEvent() {
        bt_inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iController.onInquiryChoose();
            }
        });

        bt_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iController.onBookingChoose();
            }
        });

        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iController.onLocationSearchRequested(tv_location.getText().toString());
            }
        });
    }

    public void setData(ClassDetailInfo aClassDetailInfo, ClassSummaryInfo aClassSummaryInfo, FacilityInfo aFacilityInfo)
    {
        vp_class_detail_images.setAdapter(new ClassDetailImagesViewPagerAdapter(getContext(),aClassDetailInfo.getDetailImageUrl()));
        tv_title.setText(aClassSummaryInfo.getTitle());
        tv_one_day_price.setText(aClassSummaryInfo.getOneDayPrice()+"원");
        tv_one_month_price.setText(aClassSummaryInfo.getOneMonthPrice()+"원");
        if(aClassDetailInfo.isPersonal())
            tv_progress_type.setText("개인 레슨");
        else
            tv_progress_type.setText("그룹 레슨");
        tv_location.setText(aClassDetailInfo.getAddress());
        tv_description.setText(aClassDetailInfo.getDescriptions());
        tv_prerequisite.setText(aClassDetailInfo.getPrerequisite());

        if(!aFacilityInfo.isFittingRoom())
            tv_fitting_room.setVisibility(View.GONE);
        if(!aFacilityInfo.isInstrumentRental())
            tv_instrument_rental.setVisibility(View.GONE);
        if(!aFacilityInfo.isLocker())
            tv_locker.setVisibility(View.GONE);
        if(!aFacilityInfo.isParkingLot())
            tv_parking_lot.setVisibility(View.GONE);
        if(!aFacilityInfo.isPracticeRoom())
            tv_practice_room.setVisibility(View.GONE);
        if(!aFacilityInfo.isShowerStall())
            tv_shower_stall.setVisibility(View.GONE);
        if(!aFacilityInfo.isToilet())
            tv_toilet.setVisibility(View.GONE);

        tv_refund_info.setText(aClassDetailInfo.getRefundInfo());


        ArrayList<String> times = new ArrayList<String>();
        times.addAll(aClassSummaryInfo.getTimes());
        setTimeTextVies(times);


    }

    private void setTimeTextVies(ArrayList<String> times) {
        tv_start_time_1.setText(times.get(0));
        if(times.size() >  1)
        {
            tv_start_time_2.setText(times.get(1));
            if(times.size() > 2)
            {
                tv_start_time_3.setText(times.get(2));
            }
            else
            {
                tv_start_time_3.setVisibility(View.GONE);
            }
        }
        else
        {
            tv_start_time_2.setVisibility(View.GONE);
            tv_start_time_3.setVisibility(View.GONE);
        }
    }


    public static interface IController
    {
        public void onInquiryChoose();
        public void onBookingChoose();
        public void onLocationSearchRequested(String aLocation);
    }
}
