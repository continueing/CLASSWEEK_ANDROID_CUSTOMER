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
    private static final int NUM_OF_PREPARED_RADIO_BUTTONS = 16;

    private ArrayList<Schedule> schedules;
    private RadioGroup rg_schedule_selection;
    private RadioButton []rb_schedules  = new RadioButton[NUM_OF_PREPARED_RADIO_BUTTONS];
    private OnScheduleSelectionListener onScheduleSelectionListener;
    private int selectedSchedule;


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
        rg_schedule_selection = (RadioGroup) findViewById(R.id.rg_schedule_selection);
        rb_schedules[0] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_1);
        rb_schedules[1] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_2);
        rb_schedules[2] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_3);
        rb_schedules[3] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_4);
        rb_schedules[4] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_5);
        rb_schedules[5] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_6);
        rb_schedules[6] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_7);
        rb_schedules[7] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_8);
        rb_schedules[8] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_9);
        rb_schedules[9] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_10);
        rb_schedules[10] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_11);
        rb_schedules[11] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_12);
        rb_schedules[12] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_13);
        rb_schedules[13] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_14);
        rb_schedules[14] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_15);
        rb_schedules[15] = (RadioButton) findViewById(R.id.rb_dialog_schedule_selection_16);
        addRadioButtons();
    }

    private void addRadioButtons()
    {
        int i =0;
        for(Schedule aSchedule : schedules)
        {
            rb_schedules[i].setId(i);
            rb_schedules[i].setText(aSchedule.getStartDateTime());
            rb_schedules[i].setVisibility(View.VISIBLE);
            i++;
        }
    }


    private void setEvents()
    {
        rg_schedule_selection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedSchedule = i;
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
