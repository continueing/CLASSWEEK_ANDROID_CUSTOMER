package com.blackpigstudio.classweek.main.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.etc.UserGuideActivity;
import com.blackpigstudio.classweek.main.ui.etc.exit_survey_dialog.ExitSurveyDialog;
import com.blackpigstudio.classweek.main.ui.menu.contact.ContactFragment;
import com.blackpigstudio.classweek.main.ui.navigation_drawer_menu.NavigationDrawerFragment;
import com.blackpigstudio.classweek.main.ui.menu.home.recommendation.ClassRecommendationFragment;
import com.blackpigstudio.classweek.main.ui.menu.now_taking.NowTakingClassFragment;
import com.blackpigstudio.classweek.main.ui.menu.took_before.TookBeforeClassFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private int currentMenuIndex=-1;
    private boolean isReadyToFinish = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        int previousMenuIndex = currentMenuIndex;
        currentMenuIndex = position;
        if(currentMenuIndex != previousMenuIndex) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (position) {
                case 0:
                    transaction.replace(R.id.container, new ClassRecommendationFragment()).commit();
                    break;
                case 1:
                    transaction.replace(R.id.container, new NowTakingClassFragment()).commit();
                    break;
                case 2:
                    transaction.replace(R.id.container, new TookBeforeClassFragment()).commit();
                    break;
                case 3:
                    transaction.replace(R.id.container, new ContactFragment()).commit();
                    break;
                default:
                    AppTerminator.error(this,"onNavigationDrawerItemSelected: there's no fragment");
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(!isReadyToFinish) {
                isReadyToFinish = true;
                Toast.makeText(getApplicationContext(),"한 번더 누르시면 종료됩니다.",1000).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isReadyToFinish = false;
                    }
                }, 1000);
            }
            else
            {
                UserPreference userPreference = new UserPreference(getApplicationContext());
                if(!userPreference.didUserParticipateSurvey())
                    new ExitSurveyDialog(MainActivity.this, exitSurveyDismissListener).show();
                else
                    finish();
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    ExitSurveyDialog.IDismissListener exitSurveyDismissListener = new ExitSurveyDialog.IDismissListener() {
        @Override
        public void onDismissed() {
            UserPreference userPreference = new UserPreference(getApplicationContext());
            userPreference.setUserSurveyParticipation(true);
            Toast.makeText(getApplicationContext(),"소중한 피드백 감사합니다.\n좋은 하루 되세요^^~", Toast.LENGTH_LONG).show();
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

}
