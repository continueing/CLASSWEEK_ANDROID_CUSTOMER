package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by continueing on 2014. 4. 14..
 */

public class ScheduleSelectionDialog extends Dialog {
    private ArrayList<Schedule> schedules;
    private RadioGroup rg_schedule_selection;
    private RadioButton rb_selected_schedule;

    public ScheduleSelectionDialog(Context context, ArrayList<Schedule> aSchedules) {
        super(context);
        this.schedules = new ArrayList<Schedule>();
        this.schedules.addAll(aSchedules);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_schedule_selection);
        initViews();
        setEvents();
    }

    private void initViews()
    {
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
            radio_btn.setText(aSchedule.toString());
            rg_schedule_selection.addView(radio_btn);
        }
    }


    private void setEvents()
    {
        rg_schedule_selection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb_selected_schedule = (RadioButton)radioGroup.getChildAt(i);
                Toast.makeText(getContext(),rb_selected_schedule.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
