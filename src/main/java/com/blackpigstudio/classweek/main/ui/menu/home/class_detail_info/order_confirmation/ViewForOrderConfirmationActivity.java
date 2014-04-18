package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 18..
 */
public class ViewForOrderConfirmationActivity extends AbstractViewForActivity {
    private IController iController;
    private TextView tv_class_title;
    private TextView tv_time;
    private TextView tv_start_date;
    private TextView tv_end_date;
    private TextView tv_payment_price;

    private EditText et_name;
    private EditText et_birth_date;
    private EditText et_phone_number;

    private RadioGroup rg_sex;

    // TODO: should determine what kinds of conditions and term are. Then, add to private members

    private Button bt_payment;


    public ViewForOrderConfirmationActivity(Context context, IController anIController) {
        super(context);
        this.iController = anIController;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_order_confirmation,null);
    }

    @Override
    protected void initViews() {
        bt_payment = (Button) findViewById(R.id.bt_order_confirmation_payment);
    }

    @Override
    protected void setEvent() {
        bt_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iController.onPaymentRequested();
            }
        });
    }

    public static interface IController
    {
        public void onPaymentRequested();
    }

}
