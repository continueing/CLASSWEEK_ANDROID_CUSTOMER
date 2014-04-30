package com.blackpigstudio.classweek.main.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.ui.navigation_drawer_menu.NavigationDrawerFragment;
import com.blackpigstudio.classweek.main.ui.menu.home.recommendation.ClassRecommendationFragment;
import com.blackpigstudio.classweek.main.ui.menu.now_taking.NowTakingClassFragment;
import com.blackpigstudio.classweek.main.ui.menu.took_before.TookBeforeClassFragment;
import com.google.analytics.tracking.android.EasyTracker;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private int currentMenuIndex=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
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
                    transaction.replace(R.id.container, ClassRecommendationFragment.getInstance()).commit();
                    break;
                case 1:
                    transaction.replace(R.id.container, new NowTakingClassFragment()).commit();
                    break;
                case 2:
                    transaction.replace(R.id.container, new TookBeforeClassFragment()).commit();
                    break;
                default:
                    AppTerminator.error(this,"onNavigationDrawerItemSelected: there's no fragment");
                    break;
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }
}
