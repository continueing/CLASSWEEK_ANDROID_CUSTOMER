package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 4. 14..
 */
public class ClassTypeSelectionDialog extends Dialog {
    private Button bt_class_type_select;
    private OnClassTypeSelectionListener onClassTypeSelectionListener;
    private RadioGroup rg_class_type;


    public ClassTypeSelectionDialog(Context context, OnClassTypeSelectionListener anOnClassTypeSelectionListener) {
        super(context);
        this.onClassTypeSelectionListener = anOnClassTypeSelectionListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class_type_selection);
        initViews();
        setEvents();
    }

    private void initViews()
    {
        bt_class_type_select = (Button)findViewById(R.id.bt_dialog_class_type_selection_select);
        rg_class_type = (RadioGroup)findViewById(R.id.rg_class_type_selection);
    }

    private void setEvents()
    {
        bt_class_type_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg_class_type.getCheckedRadioButtonId())
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
