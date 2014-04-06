package com.blackpigstudio.classweek.main.module.homeui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.ui.menus.home.filter.FilterActivity;

/**
 * Created by continueing on 2014. 4. 2..
 */
public class AbstractFilterActionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
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
