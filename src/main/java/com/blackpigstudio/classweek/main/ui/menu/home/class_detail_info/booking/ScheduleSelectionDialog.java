package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.booking;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.Schedule;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 14..
 */

public class ScheduleSelectionDialog extends Dialog {
    private ArrayList<Schedule> schedules;
    private RadioGroup rg_schedule_selection;
    private OnScheduleSelectionListener onScheduleSelectionListener;
    private int selectedSchedule;
    private Button bt_schedule_select;

    public ScheduleSelectionDialog(Context context, OnScheduleSelectionListener anOnScheduleSelectionListener) {
        super(context);
        this.schedules = new ArrayList<Schedule>();
        this.onScheduleSelectionListener = anOnScheduleSelectionListener;
    }

    public void setSchedules(ArrayList<Schedule> aSchedules)
    {
        this.schedules.addAll(aSchedules);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_schedule_selection);
        initViews();
        setEvents();
    }

    private void initViews()
    {
        bt_schedule_select = (Button)findViewById(R.id.bt_dialog_schedule_selection_select);
        rg_schedule_selection = (RadioGroup) findViewById(R.id.rg_schedule_selection);
        addRadioButtons();
    }

    private void addRadioButtons()
    {
        int i =0;
        for(Schedule aSchedule : schedules)
        {
            RadioButton radio_btn = new RadioButton(getContext());
            radio_btn.setId(i++);
            radio_btn.setText( "          " + aSchedule.getStartDateTime());
            radio_btn.setButtonDrawable(R.drawable.btn_check_style);
            radio_btn.setBackgroundResource(R.drawable.bg_dropdown_style);
            radio_btn.setTextColor(Color.BLACK);
            radio_btn.setPadding(10, 8, 10, 0);
            radio_btn.setTextSize(14);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
//            radio_btn.setLayoutParams(params);
            radio_btn.setWidth(335*2);
            radio_btn.setHeight(48*2);
            rg_schedule_selection.addView(radio_btn);
            if(i==0)
                radio_btn.setChecked(true);
        }
    }


    private void setEvents()
    {
        rg_schedule_selection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedSchedule = i;
            }
        });

        bt_schedule_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScheduleSelectionListener.onScheduleSelected(schedules.get(selectedSchedule));
                dismiss();
            }
        });
    }

    public static interface OnScheduleSelectionListener
    {
        public void onScheduleSelected(Schedule aSchedule);
    }
}
