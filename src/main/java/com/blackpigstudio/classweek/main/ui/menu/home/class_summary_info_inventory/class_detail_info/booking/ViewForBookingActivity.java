package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 14..
 */
public class ViewForBookingActivity extends AbstractViewForActivity {
    private Button bt_class_type_selection;
    private Button bt_schedule_selection;
    private OnClassTypeSelectionRequestListener onClassTypeSelectionRequestListener;
    private OnScheduleSelectionRequestListener onScheduleSelectionRequestListener;

    public ViewForBookingActivity(Context context, OnClassTypeSelectionRequestListener anOnClassTypeSelectionRequestListener, OnScheduleSelectionRequestListener anOnScheduleSelectionRequestListener) {
        super(context);
        onClassTypeSelectionRequestListener  = anOnClassTypeSelectionRequestListener;
        onScheduleSelectionRequestListener = anOnScheduleSelectionRequestListener;

    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_booking,null);
    }

    @Override
    protected void initViews() {
        bt_class_type_selection = (Button) findViewById(R.id.bt_booking_class_type_selection);
        bt_schedule_selection= (Button) findViewById(R.id.bt_booking_schedule_selection);
    }

    @Override
    protected void setEvent() {
        bt_class_type_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClassTypeSelectionRequestListener.onClassTypeSelectionRequested();
            }
        });

        bt_schedule_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScheduleSelectionRequestListener.onScheduleSelectionRequested();
            }
        });
    }

    public static interface OnClassTypeSelectionRequestListener
    {
        public void onClassTypeSelectionRequested();
    }

    public static interface OnScheduleSelectionRequestListener
    {
        public void onScheduleSelectionRequested();
    }
}
