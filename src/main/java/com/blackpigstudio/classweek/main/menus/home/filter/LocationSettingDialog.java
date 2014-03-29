package com.blackpigstudio.classweek.main.menus.home.filter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 3. 29..
 */
public class LocationSettingDialog extends Dialog{
    private Button bt_select;
    RadioGroup rg_location_group;
    private LocationReceiver locationReceiver;

    public LocationSettingDialog(Context context, LocationReceiver aLocationReceiver) {
        super(context);
        locationReceiver = aLocationReceiver;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_location_setting);

        setTitle("다니고싶은 학원의 지역을 선택해 주세요.");
        rg_location_group = (RadioGroup)findViewById(R.id.rg_filter_location_list);

        bt_select = (Button) findViewById(R.id.bt_filter_location_select);
        bt_select.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (rg_location_group.getCheckedRadioButtonId())
            {
                case R.id.rb_filter_location_gangnam:
                    locationReceiver.setLocation(getContext().getResources().getString(R.string.location_gangnam));
                    break;
                case R.id.rb_filter_location_gangbuk:
                    locationReceiver.setLocation(getContext().getResources().getString(R.string.location_gangbuk));
                    break;
                default:
                    Log.e(this.getClass().getCanonicalName(),"onCheckedChangeListener.onCheckedChanged: unexpected location index");
                    System.exit(-1);
                    break;
            }
            dismiss();
        }
    };


    public interface LocationReceiver
    {
        public void setLocation(String aLocation);
    }
}
