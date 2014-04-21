package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 4. 14..
 */
public class ClassTypeSelectionDialog extends Dialog {
    private OnClassTypeSelectionListener onClassTypeSelectionListener;
    private RadioGroup rg_class_type;
    private RadioButton rb_oneDay;
    private RadioButton rb_oneMonth;
    private String oneDayPrice;
    private String oneMonthPrice;
    private int numberOfClassPerMonth;


    public ClassTypeSelectionDialog(Context context, OnClassTypeSelectionListener anOnClassTypeSelectionListener, String aOneDayPrice , String aOneMonthPrice, int aNumberOfClassPerMonth) {
        super(context);
        this.onClassTypeSelectionListener = anOnClassTypeSelectionListener;
        this.oneDayPrice = aOneDayPrice;
        this.oneMonthPrice = aOneMonthPrice;
        this.numberOfClassPerMonth = aNumberOfClassPerMonth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_class_type_selection);
        initViews();
        setEvents();
    }

    private void initViews()
    {
        rg_class_type = (RadioGroup)findViewById(R.id.rg_class_type_selection);
        rb_oneDay = (RadioButton) findViewById(R.id.rb_dialog_class_type_selection_type_day);
        rb_oneDay.setText("1회 / " + oneDayPrice + "원");

        rb_oneMonth = (RadioButton) findViewById(R.id.rb_dialog_class_type_selection_type_month);
        rb_oneMonth.setText( numberOfClassPerMonth +  "회(1개월) / " + oneMonthPrice + "원");

    }

    private void setEvents()
    {
        rg_class_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_dialog_class_type_selection_type_day:
                        onClassTypeSelectionListener.onOneDaySelected();
                        break;

                    case R.id.rb_dialog_class_type_selection_type_month:
                        onClassTypeSelectionListener.onOneMonthSelected();
                        break;
                }
                ClassTypeSelectionDialog.this.dismiss();
            }
        });
    }

    public static interface OnClassTypeSelectionListener
    {
        public void onOneDaySelected();
        public void onOneMonthSelected();
    }
}
