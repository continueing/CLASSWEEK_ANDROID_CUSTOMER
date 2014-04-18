package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummeryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter.FilterActivity;

import org.json.JSONObject;

import java.util.ArrayList;

public class ClassSummaryInfoInventoryActivity extends ActionBarActivity implements OnClassSummeryInfoChooseListener, HttpRequester.NetworkResponseListener, OnScrollOfListViewListener{
    public static final int REQUEST_CODE_GET_QUERY = 0;
    private ViewForClassSummaryInfoInventoryActivity viewForClassSummaryInfoInventoryActivity;
    public static final String BUNDLE_PARM_OF_TITLE = "TITLE";
    public static final String BUNDLE_PARM_OF_URL_KEY = "URL";
    private String urlToQuery;

    private String urlKeyLocation;

    private boolean urlKeyMon;
    private boolean urlKeyTue;
    private boolean urlKeyWen;
    private boolean urlKeyTur;
    private boolean urlKeyFri;
    private boolean urlKeySat;
    private boolean urlKeySun;

    private boolean urlKeyMorning;
    private boolean urlKeyAfternoon;
    private boolean urlKeyEvening;

    private int urlKeyPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        viewForClassSummaryInfoInventoryActivity = new ViewForClassSummaryInfoInventoryActivity(this,this, this);
        setContentView(viewForClassSummaryInfoInventoryActivity.getRoot());
        Intent intent = getIntent();
        setTitle(intent.getStringExtra(BUNDLE_PARM_OF_TITLE));
        this.urlToQuery = intent.getStringExtra(BUNDLE_PARM_OF_URL_KEY);
        viewForClassSummaryInfoInventoryActivity.setProgressbarVisibility(true);
        requestClassSummaryInfoFromServer("load more");
    }

    private void requestClassSummaryInfoFromServer(String url) {
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
                startActivityForResult(intent,REQUEST_CODE_GET_QUERY);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GET_QUERY)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                //TODO: should set urlKey variables and then refresh class items
                String result = data.getStringExtra(FilterActivity.BUNDLE_PARM_OF_URL_LOCATION_KEY);
                if(data.getBooleanExtra(FilterActivity.BUNDLE_PARM_OF_URL_AFTERNOON_KEY,false))
                    result += " O";
                else
                    result += " X";
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClassSummeryInfoChoose(IClassSummaryInfoItem iClassSummaryInfoItem) {
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
            ArrayList<IClassSummaryInfoItem> classSummaryInfoItems = new ArrayList<IClassSummaryInfoItem>();
            for(int i = 1; i < 4; i++ )
                classSummaryInfoItems.add(new ClassSummaryInfo(i));
            viewForClassSummaryInfoInventoryActivity.addClassSummaryInfoItemArrayList(classSummaryInfoItems);
            viewForClassSummaryInfoInventoryActivity.setProgressbarVisibility(false);

        }
    };

    @Override
    public void onSuccess(JSONObject jsonObject) {
        tmp.sendEmptyMessage(0);
        /*
            should parse jsonObject to ArrayList of <ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem> and send to this ArrayList To view
            if the number of class Item are less than 10, FooterProgressbar should be hide.
         */
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {

    }

    @Override
    public void atScrollIsOnEndItem() {
        viewForClassSummaryInfoInventoryActivity.setProgressbarVisibility(true);
        requestClassSummaryInfoFromServer("load more");
    }
}
