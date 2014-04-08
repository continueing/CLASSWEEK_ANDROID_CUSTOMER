package com.blackpigstudio.classweek.main.ui.singn_in_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 8..
 */
public class ViewForSignInAndUpActivity extends AbstractViewForActivity {

    public ViewForSignInAndUpActivity(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_sign_in_and_up, null);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvent() {

    }
}
