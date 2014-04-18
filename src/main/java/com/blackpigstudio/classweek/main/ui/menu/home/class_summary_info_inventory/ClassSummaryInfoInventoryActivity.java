package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummeryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.filter.FilterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClassSummaryInfoInventoryActivity extends ActionBarActivity implements OnClassSummeryInfoChooseListener, HttpRequester.NetworkResponseListener, OnScrollOfListViewListener{
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
        ClassRequest classRequest = new ClassRequest();
        try {
            classRequest.getClassSummaryInfos(this.category, this.subcategory, nextPage, this);
        } catch (JSONException e) {
            e.printStackTrace();
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
        if(iClassSummaryInfoItems.size() >= 10 )
        {
            nextPage++;
        }
        else
        {
            isFinalPage = true;
            view.setProgressbarVisibility(false);
        }
        isRequestingHttpNow = false;
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        AppTerminator.error(this, "classRequest.getClassSummaryInfos fail : " + errorCode);
    }

    @Override
    public void atScrollIsOnEndItem() {
        if(!isRequestingHttpNow && !isFinalPage ) {
            requestClassSummaryInfoFromServer();
        }
    }
}
