package com.blackpigstudio.classweek.main.module;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.blackpigstudio.classweek.main.module.subcategoryspinner.HomeSpinnerAdapter;
import com.blackpigstudio.classweek.main.ui.menus.home.dance.SubcategoryFragment;
import com.blackpigstudio.classweek.main.ui.menus.home.filter.FilterActivity;
import com.blackpigstudio.classweek.main.ui.menus.home.recommendation.ClassRecommendationFragment;


abstract public class AbstractHomeFragment extends Fragment {
    private int spinnerItemIndexOfThisFragment;

    public AbstractHomeFragment(int aSpinnerItemIndex) {
        this.spinnerItemIndexOfThisFragment = aSpinnerItemIndex;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);

        ActionBar actionBar  = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        SpinnerAdapter spinnerAdapter = HomeSpinnerAdapter.getInstance(getActivity().getApplicationContext());
        actionBar.setListNavigationCallbacks(spinnerAdapter, new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                if(i != spinnerItemIndexOfThisFragment) {
                    switch (i) {
                        case 0:
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ClassRecommendationFragment.getInstance()).commit();
                            break;
                        case 1:
                            ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, SubcategoryFragment.getInstance()).commit();
                            break;
                        case 2:
                            break;
                        default:
                            Log.e(this.getClass().getCanonicalName(), "OnNavigationListener.onNavigationItemSelected: unexpected spinner item index");
                            System.exit(-1);
                            break;
                    }
                }
                return false;
            }
        });
        actionBar.setSelectedNavigationItem(spinnerItemIndexOfThisFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_class_filter:
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivityForResult(intent,FilterActivity.REQUEST_CODE_GET_QUERY);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == FilterActivity.REQUEST_CODE_GET_QUERY)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                //TODO: should call filtered fragment.
                Log.e("Test", "test");
                String result = data.getStringExtra(FilterActivity.INTENT_PARM_QUERY);
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            }
        }
    }

}
