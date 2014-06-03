package com.blackpigstudio.classweek.main.ui.admin.front_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.blackpigstudio.classweek.main.ui.admin.login_page.LoginActivity;
import com.blackpigstudio.classweek.main.ui.admin.sign_up_page.SignUpActivity;

public class FrontActivity extends Activity implements ViewForFrontActivity.Controller {
    public static final String BUNDLE_PARM_IS_FIRST_ENTRANCE = "IS_FIRST_ENTRANCE";
    private ViewForFrontActivity view;
    private boolean isFirstEntrance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        isFirstEntrance = intent.getBooleanExtra(BUNDLE_PARM_IS_FIRST_ENTRANCE,false);
        view = new ViewForFrontActivity(getApplicationContext(),this);
        setContentView(view.getRoot());
        if(!isFirstEntrance)
        {
            view.hideSkipTextView();
            view.showNeedToLoginTextView();
        }
    }


    @Override
    public void onBackPressed() {
        if(isFirstEntrance)
            callMainActivity();
        finish();
    }

    @Override
    public void onSingUpRequested() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, SignUpActivity.REQUEST_FOR_ACTIVITY_RESULT);
    }

    @Override
    public void onLoginRequested() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,LoginActivity.REQUEST_FOR_ACTIVITY_RESULT);
    }

    @Override
    public void onSkipRequested() {
        callMainActivity();
        finish();
    }

    private void callMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UserPreference userPreference = new UserPreference(getApplicationContext());
        if(userPreference.isLoggedIn())
        {
            if(isFirstEntrance)
                callMainActivity();
            finish();
        }
    }
}
