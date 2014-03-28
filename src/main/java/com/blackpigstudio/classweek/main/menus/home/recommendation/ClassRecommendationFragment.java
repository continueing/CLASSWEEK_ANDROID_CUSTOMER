package com.blackpigstudio.classweek.main.menus.home.recommendation;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.MainActivity;
import com.blackpigstudio.classweek.main.menus.home.filter.FilterActivity;
import com.blackpigstudio.classweek.main.menus.home.spinner.HomeSpinnerAdapter;
import com.blackpigstudio.classweek.main.menus.home.spinner.OnHomeSpinnerNavigationListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends Fragment {


    public ClassRecommendationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        ClassRecommendationView classRecommendationView = new ClassRecommendationView(getActivity().getApplicationContext(),container);
        return classRecommendationView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
        ActionBar actionBar  = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        SpinnerAdapter spinnerAdapter = new HomeSpinnerAdapter(getActivity().getApplicationContext());
        actionBar.setListNavigationCallbacks(spinnerAdapter,new OnHomeSpinnerNavigationListener());
        // 변수로 저장해둬야함. 어디 드랍다운이였는지
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
                Log.e("Test", "test");
                String result = data.getStringExtra(FilterActivity.INTENT_PARM_QUERY);
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            }
            else
            {
                Log.e("Test", "NOP");
            }
        }

    }
}
