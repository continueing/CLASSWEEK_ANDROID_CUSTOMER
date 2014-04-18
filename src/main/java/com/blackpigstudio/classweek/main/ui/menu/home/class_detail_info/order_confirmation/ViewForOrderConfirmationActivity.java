package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 18..
 */
public class ViewForOrderConfirmationActivity extends AbstractViewForActivity {

    public ViewForOrderConfirmationActivity(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_order_confirmation,null);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvent() {

    }
}
