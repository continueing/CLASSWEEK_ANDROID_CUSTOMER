package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;
import com.loopj.android.image.SmartImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by continueing on 2014. 4. 18..
 */
public class ViewForOrderConfirmationActivity extends AbstractViewForActivity {
    private IController iController;
    private SmartImageView siv_front;
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
        tv_class_title = (TextView) findViewById(R.id.tv_order_confirmation_class_title);
        tv_time = (TextView) findViewById(R.id.tv_order_confirmation_time);
        tv_start_date = (TextView) findViewById(R.id.tv_order_confirmation_start_date);
        tv_end_date = (TextView) findViewById(R.id.tv_order_confirmation_end_date);
        tv_payment_price = (TextView) findViewById(R.id.tv_order_confirmation_payment_price);
        siv_front = (SmartImageView)findViewById(R.id.siv_order_confirmation_class_front);

        et_name = (EditText) findViewById(R.id.et_order_confirmation_name);
        et_birth_date = (EditText) findViewById(R.id.et_order_confirmation_birth_date);
        et_phone_number = (EditText) findViewById(R.id.et_order_confirmation_phone_number);
    }

    @Override
    protected void setEvent() {
        bt_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern pattern = Pattern.compile("^[가-힣]{2,4}$");
                Matcher matcher = pattern.matcher(et_name.getText());
                if(!matcher.matches())
                {
                    Toast.makeText(getContext(),"이름은 2글자에서 4글자 사이입니다.",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!isValidDate(et_birth_date.getText().toString()))
                {
                    Toast.makeText(getContext(),"생년월일(YYYY-MM-DD) 예시는 다음과 같습니다. 1990-02-02",Toast.LENGTH_LONG).show();
                    return;
                }
                pattern = Pattern.compile("^01(0|1|[6-9])-(\\d{3}|\\d{4})-\\d{4}$");
                matcher = pattern.matcher(et_phone_number.getText());
                if(!matcher.matches())
                {
                    Toast.makeText(getContext(),"전화번호 예시는 다음과 같습니다. 010-4527-9272",Toast.LENGTH_LONG).show();
                    return;
                }
                iController.onPaymentRequested();
            }
        });
    }

    public boolean isValidDate(String aYYYYMMdd)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (aYYYYMMdd == null) return false;
        String format = null;
        try {
            format = sdf.format(sdf.parse(aYYYYMMdd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return aYYYYMMdd.equals(format);

    }

    public void setData(String aClassTitle, String aTime, String aStartDate, String aEndDate, String aPaymentPrice, String aImageUrl)
    {
        tv_class_title.setText(aClassTitle);
        tv_time.setText(aTime);
        tv_start_date.setText(aStartDate);
        tv_end_date.setText(aEndDate);
        tv_payment_price.setText(aPaymentPrice);
        siv_front.setImageUrl(aImageUrl);
    }

    public static interface IController
    {
        public void onPaymentRequested();
    }

}
