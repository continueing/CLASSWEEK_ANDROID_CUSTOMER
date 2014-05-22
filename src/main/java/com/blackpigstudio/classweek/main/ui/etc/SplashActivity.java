package com.blackpigstudio.classweek.main.ui.etc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.MainActivity;

public class SplashActivity extends Activity {
    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed( new Runnable() {

            @Override
            public void run() {
                Intent i=null;
                UserPreference userPreference = new UserPreference(getApplicationContext());
                if(userPreference.isUserEntered() == false) {
                    i = new Intent( SplashActivity.this, UserGuideActivity.class );
                    userPreference.setUserEntered(true);
                }
                else
                {
                    i = new Intent( SplashActivity.this, MainActivity.class );
                }
                startActivity(i);
                fileList();
                finish();
                SplashActivity.this.overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
                // TODO should check version, login state, gcm, google analytics
            }

        }, SPLASH_TIME_OUT );
    }




}
