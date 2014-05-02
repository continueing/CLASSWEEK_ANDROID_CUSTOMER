package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;
import com.blackpigstudio.classweek.main.module.button.SubmitButton;
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

    private CheckBox cb_agree_all;
    private CheckBox cb_agreement;
    private CheckBox cb_personal_information_handling_policy;
    private CheckBox cb_precautions_of_payment;



    private EditText et_name;
    private EditText et_birth_date;
    private EditText et_phone_number;
    private RadioGroup radioGroup;

    private ScrollView sv_entire;
    private ScrollView sv_agreement;
    private ScrollView sv_personal_information_handling_policy;
    private ScrollView sv_precautions_of_payment;


    private SubmitButton bt_payment;



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
        tv_class_title = (TextView) findViewById(R.id.tv_order_confirmation_class_title);
        tv_time = (TextView) findViewById(R.id.tv_order_confirmation_time);
        tv_start_date = (TextView) findViewById(R.id.tv_order_confirmation_start_date);
        tv_end_date = (TextView) findViewById(R.id.tv_order_confirmation_end_date);
        tv_payment_price = (TextView) findViewById(R.id.tv_order_confirmation_payment_price);
        siv_front = (SmartImageView)findViewById(R.id.siv_order_confirmation_class_front);

        et_name = (EditText) findViewById(R.id.et_order_confirmation_name);
        et_birth_date = (EditText) findViewById(R.id.et_order_confirmation_birth_date);
        et_phone_number = (EditText) findViewById(R.id.et_order_confirmation_phone_number);

        radioGroup = (RadioGroup)findViewById(R.id.rg_order_confirmation_sex);


        cb_agree_all = (CheckBox)findViewById(R.id.cb_agree_all);
        cb_agreement = (CheckBox)findViewById(R.id.cb_agreement);
        cb_personal_information_handling_policy  = (CheckBox)findViewById(R.id.cb_personal_information_handling_policy);
        cb_precautions_of_payment  = (CheckBox)findViewById(R.id.cb_precautions_of_payment);

        sv_entire = (ScrollView)findViewById(R.id.sv_order_confirmation_entire);
        sv_agreement = (ScrollView)findViewById(R.id.sv_order_confirmation_agreement);
        sv_personal_information_handling_policy = (ScrollView)findViewById(R.id.sv_order_confirmation_personal_information_handling_policy);
        sv_precautions_of_payment = (ScrollView)findViewById(R.id.sv_order_confirmation_precautions_of_payment);

        bt_payment = (SubmitButton) findViewById(R.id.bt_order_confirmation_payment);
        bt_payment.init((ProgressBar)findViewById(R.id.pg_order_confirmation_payment));
        bt_payment.setEnabled(false);
        bt_payment.addViewToHold(et_name);
        bt_payment.addViewToHold(et_birth_date);
        bt_payment.addViewToHold(et_phone_number);
        bt_payment.addViewToHold(findViewById(R.id.rb_order_confirmation_male));
        bt_payment.addViewToHold(findViewById(R.id.rb_order_confirmation_female));
    }

    @Override
    protected void setEvent() {
        cb_agree_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_agreement.setChecked(true);
                    cb_personal_information_handling_policy.setChecked(true);
                    cb_precautions_of_payment.setChecked(true);
                }
                else
                {
                    cb_agreement.setChecked(false);
                    cb_personal_information_handling_policy.setChecked(false);
                    cb_precautions_of_payment.setChecked(false);
                }

                bt_payment.setEnabled(canSubmitButtonBeEnable());

            }
        });

        cb_agreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bt_payment.setEnabled(canSubmitButtonBeEnable());
            }
        });

        cb_personal_information_handling_policy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bt_payment.setEnabled(canSubmitButtonBeEnable());
            }
        });

        cb_precautions_of_payment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bt_payment.setEnabled(canSubmitButtonBeEnable());
            }
        });

        bt_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern pattern = Pattern.compile("^[가-힣]{2,4}$");
                Matcher matcher = pattern.matcher(et_name.getText());
                if(!matcher.matches())
                {
                    Toast.makeText(getContext(),"이름은 2글자에서 4글자 사이입니다.",Toast.LENGTH_LONG).show();
                    releaseSubmitButton();
                    return;
                }
                if(!isValidDate(et_birth_date.getText().toString()))
                {
                    Toast.makeText(getContext(),"생년월일(YYYY-MM-DD) 예시는 다음과 같습니다. 1990-02-02",Toast.LENGTH_LONG).show();
                    releaseSubmitButton();
                    return;
                }
                pattern = Pattern.compile("^01(0|1|[6-9])-(\\d{3}|\\d{4})-\\d{4}$");
                matcher = pattern.matcher(et_phone_number.getText());
                if(!matcher.matches())
                {
                    Toast.makeText(getContext(),"전화번호 예시는 다음과 같습니다. 010-4527-9272",Toast.LENGTH_LONG).show();
                    releaseSubmitButton();
                    return;
                }
                iController.onPaymentRequested();
            }
        });

        sv_entire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sv_agreement.getParent().requestDisallowInterceptTouchEvent(false);
                sv_personal_information_handling_policy.getParent().requestDisallowInterceptTouchEvent(false);
                sv_precautions_of_payment.getParent().requestDisallowInterceptTouchEvent(false);
                sv_entire.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        sv_agreement.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        sv_precautions_of_payment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        sv_personal_information_handling_policy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public boolean canSubmitButtonBeEnable()
    {
        if(!cb_agreement.isChecked())
            return false;
        if(!cb_personal_information_handling_policy.isChecked())
            return false;
        if(!cb_precautions_of_payment.isChecked())
            return false;
        return true;
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

    public String getName() {
        return et_name.getText().toString();
    }

    public String getBirthDate() {
        return et_birth_date.getText().toString();
    }

    public String getPhoneNumber() {
        return et_phone_number.getText().toString();
    }

    public String getSex()
    {
        if(radioGroup.getCheckedRadioButtonId() == R.id.rb_order_confirmation_male)
            return "M";
        else
            return "W";
    }

    public void releaseSubmitButton()
    {
        this.bt_payment.release();
    }

    public static interface IController
    {
        public void onPaymentRequested();
    }

}
