package com.blackpigstudio.classweek.main.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.blackpigstudio.classweek.R;

public class SplashActivity extends Activity {
    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed( new Runnable() {

            @Override
            public void run() {
                Intent i;
                i = new Intent( SplashActivity.this, MainActivity.class );
                startActivity(i);
                fileList();
                finish();
                SplashActivity.this.overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
                // TODO should check version, login state, gcm, google analytics
            }

        }, SPLASH_TIME_OUT );
    }




}
