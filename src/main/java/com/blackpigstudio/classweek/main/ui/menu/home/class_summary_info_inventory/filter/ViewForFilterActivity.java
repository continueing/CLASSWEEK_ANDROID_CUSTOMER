package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;
import com.blackpigstudio.classweek.main.module.button.CheckButton;

/**
 * Created by continueing on 2014. 3. 28..
 */

public class ViewForFilterActivity extends AbstractViewForActivity {
    /*
        view members
     */
    private RelativeLayout rl_location_setting;
    private TextView tv_location;

    private CheckButton cb_mon;
    private CheckButton cb_tue;
    private CheckButton cb_wen;
    private CheckButton cb_thu;
    private CheckButton cb_fri;
    private CheckButton cb_sat;
    private CheckButton cb_sun;

    private CheckButton cb_morning;
    private CheckButton cb_afternoon;
    private CheckButton cb_evening;

    private TextView tv_price_value;
    private SeekBar sb_price_controller;
    private Button bt_search;
    private Button bt_reset;


    /*
        constructor
     */
    public ViewForFilterActivity(Context context, OnSubmitButtonClickListener onSubmitButtonClickListener, OnLocationSettingDialogPopupRequestListener onLocationSettingDialogPopupRequestListener) {
        super(context);
        this.onSubmitButtonClickListener = onSubmitButtonClickListener;
        this.onLocationSettingDialogPopupRequestListener = onLocationSettingDialogPopupRequestListener;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_filter, null);
    }

    /*
            Initialization of views
         */
    @Override
    protected void initViews() {
        rl_location_setting = (RelativeLayout) findViewById(R.id.rl_filter_location_setting);
        tv_location = (TextView)findViewById(R.id.tv_filter_location);

        // Day setting
        cb_mon = (CheckButton)findViewById(R.id.cb_mon);
        cb_tue = (CheckButton)findViewById(R.id.cb_tue);
        cb_wen = (CheckButton)findViewById(R.id.cb_wen);
        cb_thu = (CheckButton)findViewById(R.id.cb_thu);
        cb_fri  = (CheckButton)findViewById(R.id.cb_fri);
        cb_sat = (CheckButton)findViewById(R.id.cb_sat);
        cb_sun = (CheckButton)findViewById(R.id.cb_sun);

        // Time setting
        cb_morning = (CheckButton)findViewById(R.id.cb_morning);
        cb_afternoon = (CheckButton)findViewById(R.id.cb_daytime);
        cb_evening = (CheckButton)findViewById(R.id.cb_evening);

        // Price
        tv_price_value = (TextView)findViewById(R.id.tv_filter_seekbar_value);
        sb_price_controller = (SeekBar)findViewById(R.id.sb_price_controller);

        //Submit
        bt_search = (Button) findViewById(R.id.bt_filter_search);
        bt_reset = (Button) findViewById(R.id.bt_filter_reset);
    }


    @Override
    protected void setEvent() {

        sb_price_controller.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_price_value.setText( i * 1000 + "원");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseAllCheckBoxes();
                tv_price_value.setText("100000원");
                sb_price_controller.setProgress(100);
                tv_location.setText("");
            }
        });

        bt_search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSearchable())
                    onSubmitButtonClickListener.onSearchConditionDelivered(tv_location.getText().toString(), cb_mon.isChecked(), cb_tue.isChecked(), cb_wen.isChecked(), cb_thu.isChecked(), cb_fri.isChecked(), cb_sat.isChecked(), cb_sun.isChecked(), cb_morning.isChecked(), cb_afternoon.isChecked(), cb_evening.isChecked(), sb_price_controller.getProgress() * 1000);
                else
                {
                    Toast.makeText(getContext(),"가능한 위치, 요일, 시간을 지정해 주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });

        rl_location_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLocationSettingDialogPopupRequestListener.onPopupRequestDelivered();

            }
        });
    }



    public void releaseAllCheckBoxes()
    {
        cb_morning.setChecked(false);
        cb_afternoon.setChecked(false);
        cb_evening.setChecked(false);

        cb_mon.setChecked(false);
        cb_tue.setChecked(false);
        cb_wen.setChecked(false);
        cb_thu.setChecked(false);
        cb_fri.setChecked(false);
        cb_sat.setChecked(false);
        cb_sun.setChecked(false);
    }

    public boolean isSearchable()
    {
        if(
          !(cb_morning.isChecked()  ||
            cb_afternoon.isChecked()||
            cb_evening.isChecked())  )
            return false;

        if(
          !(cb_mon.isChecked()||
            cb_tue.isChecked()||
            cb_wen.isChecked()||
            cb_thu.isChecked()||
            cb_fri.isChecked()||
            cb_sat.isChecked()||
            cb_sun.isChecked()))
            return false;

        if("".equals(tv_location.getText()))
            return false;

        return true;
    }

    /*
        Interfaces
     */
    public interface OnSubmitButtonClickListener
    {
        void onSearchConditionDelivered(String aLocation, boolean mon, boolean tue, boolean wen, boolean tur, boolean fri, boolean sat, boolean sun, boolean morning, boolean afternoon, boolean evening, int aPrice  );
    }

    public interface OnLocationSettingDialogPopupRequestListener
    {
        void onPopupRequestDelivered();
    }

    private OnSubmitButtonClickListener onSubmitButtonClickListener;
    private OnLocationSettingDialogPopupRequestListener onLocationSettingDialogPopupRequestListener;


    /*
        not categorized yet
     */
    public void setLocation(String aLocation) {
        tv_location.setText(aLocation);
    }


}
