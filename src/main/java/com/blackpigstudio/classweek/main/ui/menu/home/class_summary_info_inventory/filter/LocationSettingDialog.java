package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;

/**
 * Created by continueing on 2014. 3. 29..
 */
public class LocationSettingDialog extends Dialog{
    private Button bt_select;
    RadioGroup rg_location_group;
    private ILocationReceiver ILocationReceiver;

    public LocationSettingDialog(Context context, ILocationReceiver aILocationReceiver) {
        super(context);
        ILocationReceiver = aILocationReceiver;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_location_setting);
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
                    ILocationReceiver.setLocation(getContext().getResources().getString(R.string.location_gangnam));
                    break;
                case R.id.rb_filter_location_gangbuk:
                    ILocationReceiver.setLocation(getContext().getResources().getString(R.string.location_gangbuk));
                    break;
                default:
                    AppTerminator.error(this, "onCheckedChangeListener.onCheckedChanged: unexpected location index");
                    break;
            }
            dismiss();
        }
    };


    public interface ILocationReceiver
    {
        public void setLocation(String aLocation);
    }
}
