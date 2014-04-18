package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.inquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class ViewForInquiryActivity  extends AbstractViewForActivity{
    public ViewForInquiryActivity(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_inquiry,null);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvent() {

    }
}
