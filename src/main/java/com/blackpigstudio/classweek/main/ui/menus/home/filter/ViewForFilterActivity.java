package com.blackpigstudio.classweek.main.ui.menus.home.filter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.templatemethodview.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 3. 28..
 */

public class ViewForFilterActivity extends AbstractViewForActivity implements LocationSettingDialog.LocationReceiver{
    /*
        view members
     */
    private Button bt_location_setting;
    private CheckBox[] cb_days;
    private CheckBox[] cb_times;
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

        cb_times = new CheckBox[3];
        cb_days = new CheckBox[7];

        initViews();
        setEvent();
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
        bt_location_setting = (Button) findViewById(R.id.bt_filter_location_setting);

        // Day setting
        cb_days[0] = (CheckBox)findViewById(R.id.cb_mon);
        cb_days[1] = (CheckBox)findViewById(R.id.cb_tue);
        cb_days[2] = (CheckBox)findViewById(R.id.cb_wen);
        cb_days[3] = (CheckBox)findViewById(R.id.cb_thu);
        cb_days[4] = (CheckBox)findViewById(R.id.cb_fri);
        cb_days[5] = (CheckBox)findViewById(R.id.cb_sat);
        cb_days[6] = (CheckBox)findViewById(R.id.cb_sun);

        // Time setting
        cb_times[0] = (CheckBox)findViewById(R.id.cb_morning);
        cb_times[1] = (CheckBox)findViewById(R.id.cb_daytime);
        cb_times[2] = (CheckBox)findViewById(R.id.cb_evening);

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
                tv_price_value.setText( i + "원");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // should write the logic of reset
                releaseAllCheckBoxes(cb_days);
                releaseAllCheckBoxes(cb_times);
                tv_price_value.setText("10000원");
                sb_price_controller.setProgress(10000);
                bt_location_setting.setText("위치 선택하기");
            }
        });

        bt_search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // result format "장소:강남/요일:OXOXXOO/시간:OXX/금액:123123원"
                String query = "";
                if(getContext().getResources().getString(R.string.filter_location_setting_default).equals(bt_location_setting.getText()))
                    query = ""+ bt_location_setting.getText();
                else
                    query = "장소:"+ bt_location_setting.getText()+"/";

                query += "요일:" + parseValueOfCheckBoxes(cb_days) +"/";
                query += "시간:" + parseValueOfCheckBoxes(cb_times)+"/";
                query += "금액:" + sb_price_controller.getProgress() + "원" ;
                onSubmitButtonClickListener.onSearchQueryDelivered(query);
            }
        });


        bt_location_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLocationSettingDialogPopupRequestListener.onPopupRequestDelivered();
            }
        });
    }




    /*
        about check box related functions
     */
    public String parseValueOfCheckBoxes(CheckBox[] aCheckBoxs)
    {
        String result = "";

        for(CheckBox checkbox : aCheckBoxs) {
            if (checkbox.isChecked() == true) {
                result += "O";
            } else {
                result += "X";
            }
        }
        return result;
    }

    public void releaseAllCheckBoxes(CheckBox[] checkBoxes)
    {
        for(CheckBox aCheckBox : checkBoxes)
        {
            aCheckBox.setChecked(false);
        }
    }

    /*
        Interfaces
     */
    public interface OnSubmitButtonClickListener
    {
        void onSearchQueryDelivered(String aQuery);
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

    @Override
    public void setLocation(String aLocation) {
        bt_location_setting.setText(aLocation);
    }


}
