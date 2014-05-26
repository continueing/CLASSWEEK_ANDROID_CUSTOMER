package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.etc.EventOfGoogleAnalytics;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummaryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter.FilterActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClassSummaryInfoInventoryActivity extends ActionBarActivity implements OnClassSummaryInfoChooseListener, HttpRequester.NetworkResponseListener, OnScrollOfListViewListener{
    public static final String SCREEN_NAME = "inventory";
    public static final int REQUEST_CODE_GET_QUERY = 0;
    private ViewForClassSummaryInfoInventoryActivity view;
    public static final String BUNDLE_PARM_OF_CATEGORY_URL = "CATEGORY_FOR_URL";
    public static final String BUNDLE_PARM_OF_SUBCATEGORY_FOR_URL = "SUBCATEGORY_FOR_URL";
    public static final String BUNDLE_PARM_OF_SUBCATEGORY_KOR = "SUBCATEGORY_KOR";

    private int nextPage = 1;
    private boolean isFinalPage = false;
    private boolean isRequestingHttpNow = false;

    private String category;
    private String subcategory;
    private String weekDay=null;
    private String location=null;
    private String time=null;
    private String price=null;
    private EasyTracker easyTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getActionBar().setIcon(android.R.color.transparent);
        view = new ViewForClassSummaryInfoInventoryActivity(this,this, this);
        setContentView(view.getRoot());
        Intent intent = getIntent();
        setTitle(intent.getStringExtra(BUNDLE_PARM_OF_SUBCATEGORY_KOR));
        this.category = intent.getStringExtra(BUNDLE_PARM_OF_CATEGORY_URL);
        this.subcategory = intent.getStringExtra(BUNDLE_PARM_OF_SUBCATEGORY_FOR_URL);
        view.setProgressbarVisibility(true);
        requestClassSummaryInfoFromServer();
    }

    private void requestClassSummaryInfoFromServer() {
        isRequestingHttpNow = true;
        ClassRequest classRequest = new ClassRequest(getApplicationContext());
        try {
            classRequest.getClassSummaryInfos(this.category, this.subcategory, weekDay, location, time, price, nextPage, this);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getClassSummaryInfos fail :"+ e.toString());
        }
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
                nextPage=1;
                isFinalPage = false;
                isRequestingHttpNow = false;
                view.clearListViewItems();
                view.setProgressbarVisibility(true);
                weekDay = data.getStringExtra(FilterActivity.BUNDLE_PARM_OF_URL_WEEK_DAY_KEY);
                location = data.getStringExtra(FilterActivity.BUNDLE_PARM_OF_URL_LOCATION_KEY);
                time = data.getStringExtra(FilterActivity.BUNDLE_PARM_OF_URL_TIME_KEY);
                price = data.getStringExtra(FilterActivity.BUNDLE_PARM_OF_URL_PRICE_KEY);
                requestClassSummaryInfoFromServer();
            }
        }
    }

    @Override
    public void onClassSummaryInfoChoose(IClassSummaryInfoItem iClassSummaryInfoItem) {
        easyTracker.send(
                MapBuilder.createEvent(
                        EventOfGoogleAnalytics.CATEGORY_VIEW,
                        EventOfGoogleAnalytics.ACTION_CLASS,
                        subcategory,
                        (long) iClassSummaryInfoItem.getClassId()
                ).build()
        );
        Intent intent = new Intent(getApplicationContext(), ClassDetailInfoActivity.class);
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_CLASS_ID, iClassSummaryInfoItem.getClassId());
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_SCHEDULE_ID, iClassSummaryInfoItem.getScheduleId());
        startActivity(intent);
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        JSONArray jsonArray = null;

        try {
            jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
        } catch (JSONException e) {
            AppTerminator.error(this, "JSONObject.getJSONArray(): " + e.toString());
        }

        ArrayList<IClassSummaryInfoItem> iClassSummaryInfoItems = new ArrayList<IClassSummaryInfoItem>();

        for(int i = 0 ; i < jsonArray.length() ; i++)
        {
            JSONObject jsonClassSummaryInfoObject = null;
            try {
                jsonClassSummaryInfoObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                AppTerminator.error(this, "jsonArray.getJSONObject: " + e.toString());
            }

            try {
                iClassSummaryInfoItems.add(new ClassSummaryInfo(jsonClassSummaryInfoObject));
            } catch (JSONException e) {
                AppTerminator.error(this, "ClassSummaryInfo.new: " + e.toString());
            }
        }

        view.addClassSummaryInfoItemArrayList(iClassSummaryInfoItems);
        isFinalPage = checkIsFinalPage(iClassSummaryInfoItems.size());
        isRequestingHttpNow = false;
    }

    private boolean checkIsFinalPage(int aSizeOfClassSummaryInfos)
    {
        if(aSizeOfClassSummaryInfos >= 10 ) // not yet
        {
            nextPage++;
            return false;
        }
        else // final
        {
            view.setProgressbarVisibility(false);
            return true;
        }
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            AppTerminator.finishActivityWithToast("인터넷 연결을 확인해 주세요", this);
        }
        else
            AppTerminator.error(this, "classRequest.getClassSummaryInfos fail : " + errorCode);
    }

    @Override
    public void atScrollIsOnEndItem() {
        if(!isRequestingHttpNow && !isFinalPage ) {
            requestClassSummaryInfoFromServer();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME + "/" + subcategory);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        easyTracker.activityStop(this);
    }
}
