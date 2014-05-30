package com.blackpigstudio.classweek.main.ui.admin.front_page;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 5. 29..
 */
public class ViewForFrontActivity extends AbstractViewForActivity {
    private Button bt_sing_up;
    private Button bt_login;
    private TextView tv_skip;
    private TextView tv_need_to_login;
    private Controller controller;

    public ViewForFrontActivity(Context context, Controller aController) {
        super(context);
        controller = aController;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_front, null);
    }

    @Override
    protected void initViews() {
        bt_sing_up = (Button)findViewById(R.id.bt_admin_front_sign_up);
        bt_login = (Button)findViewById(R.id.bt_admin_front_login);
        tv_skip = (TextView)findViewById(R.id.tv_admin_front_skip);
        tv_skip.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_need_to_login = (TextView)findViewById(R.id.tv_admin_front_need_login);
    }

    @Override
    protected void setEvent() {
        bt_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onSingUpRequested();
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onLoginRequested();
            }
        });

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onSkipRequested();
            }
        });
    }

    public void hideSkipTextView()
    {
        tv_skip.setVisibility(View.INVISIBLE);
    }

    public void showNeedToLoginTextView()
    {
        tv_need_to_login.setVisibility(View.VISIBLE);
    }

    public static interface Controller
    {
        public void onSingUpRequested();
        public void onLoginRequested();
        public void onSkipRequested();
    }
}
