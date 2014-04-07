package com.blackpigstudio.classweek.main.ui.menus.home.class_summary_info_inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.classsummaryinfolistview.ViewForClassSummaryInfoListViewItem;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.ui.menus.home.class_detail_info.ClassDetailInfoActivity;
import com.blackpigstudio.classweek.main.ui.menus.home.filter.FilterActivity;

import org.json.JSONObject;

import java.util.ArrayList;

public class ClassSummeryInfoInventoryActivity extends ActionBarActivity implements ViewForClassSummeryInfoInventoryActivity.OnClassSummeryInfoChooseListener, HttpRequester.NetworkResponseListener{
    private ViewForClassSummeryInfoInventoryActivity viewForClassSummeryInfoInventoryActivity;
    public static final String BUNDLE_PARM_OF_TITLE = "title";
    public static final String BUNDLE_PARM_OF_URL_KEY = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        viewForClassSummeryInfoInventoryActivity = new ViewForClassSummeryInfoInventoryActivity(this,this);
        setContentView(viewForClassSummeryInfoInventoryActivity.getRoot());

        Intent intent = getIntent();
        setTitle(intent.getStringExtra(BUNDLE_PARM_OF_TITLE));
        requestClassSummaryInfoFromServer(" classweek/" + intent.getStringExtra(BUNDLE_PARM_OF_URL_KEY));
    }

    private void requestClassSummaryInfoFromServer(String url) {
        setSupportProgressBarIndeterminateVisibility(true);
        HttpRequester.foo(this, url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_summray_info_inventory, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_class_filter:
                Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
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
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClassSummeryInfoChoose(ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem iClassSummaryInfoItem) {
        Intent intent = new Intent(getApplicationContext(), ClassDetailInfoActivity.class);
        startActivity(intent);
    }



    /*
        temporal handler for dummy ClassSummaryInfos
     */
    Handler tmp = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem> classSummaryInfoItems = new ArrayList<ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem>();
            for(int i = 1; i < 80; i++ )
                classSummaryInfoItems.add(new ClassSummaryInfo(i));
            viewForClassSummeryInfoInventoryActivity.addClassSummaryInfoItemArrayList(classSummaryInfoItems);

            setSupportProgressBarIndeterminateVisibility(false);

        }
    };




    @Override
    public void onSuccess(JSONObject jsonObject) {
        tmp.sendEmptyMessage(0);
        /*
            should parse jsonObject to ArrayList of <ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem> and send to this ArrayList To view
         */

    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {

    }
}
