package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 14..
 */
public class ViewForBookingActivity extends AbstractViewForActivity {
    private RelativeLayout rl_class_type_selection;
    private TextView tv_selected_class_type;
    private TextView tv_selected_class_price;

    private RelativeLayout rl_booking_schedule_selection;
    private TextView tv_selected_schedule;
    private Button bt_payment;
    private IViewListener iViewListener;


    public ViewForBookingActivity(Context context, IViewListener anIViewListener) {
        super(context);
        iViewListener = anIViewListener;

    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_booking,null);
    }

    @Override
    protected void initViews() {
        rl_class_type_selection = (RelativeLayout) findViewById(R.id.rl_booking_class_type_selection);
        tv_selected_class_type = (TextView) findViewById(R.id.tv_booking_selected_class_type);
        tv_selected_class_price = (TextView) findViewById(R.id.tv_booking_selected_class_price);

        rl_booking_schedule_selection = (RelativeLayout) findViewById(R.id.rl_booking_schedule_selection);
        tv_selected_schedule = (TextView) findViewById(R.id.tv_booking_selected_schedule);

        bt_payment = (Button)findViewById(R.id.bt_booking_payment);
        bt_payment.setEnabled(false);
    }

    @Override
    protected void setEvent() {
        rl_class_type_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iViewListener.onClassTypeSelectionRequested();
            }
        });

        rl_booking_schedule_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iViewListener.onScheduleSelectionRequested();
            }
        });

        bt_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iViewListener.onPaymentRequested();
            }
        });
    }

    public void setClassTypeAndPriceTextView(String aClassType, String aPrice)
    {
        this.tv_selected_class_type.setText(aClassType);
        this.tv_selected_class_price.setText(aPrice+"원");
    }

    public boolean isSetClassTypeTextView()
    {
        if(this.tv_selected_class_type.equals(getContext().getResources().getString(R.string.class_selection)))
            return false;
        else
            return true;
    }

    public void setScheduleTextView(String aSchedule)
    {
        this.tv_selected_schedule.setText(aSchedule);
    }

    public boolean isSetScheduleTextView()
    {
        if(this.tv_selected_schedule.equals(getContext().getResources().getString(R.string.schedule_selection)))
            return false;
        else
            return true;
    }

    public void enablePayment()
    {
        this.bt_payment.setEnabled(true);
    }

    public static interface IViewListener
    {
        public void onScheduleSelectionRequested();
        public void onClassTypeSelectionRequested();
        public void onPaymentRequested();
    }

}
