package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.inquiry;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.singn_in_up.SignInAndUpActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.json.JSONException;
import org.json.JSONObject;

public class InquiryActivity extends ActionBarActivity implements HttpRequester.NetworkResponseListener {
    public static final String SCREEN_NAME = "inquiry";
    public static final String BUNDLE_PARM_CLASS_ID = "classes_id";

    private MenuItem mi_inquiry;
    private ViewForInquiryActivity view;
    private ClassRequest classRequest;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getActionBar().setIcon(android.R.color.transparent);
        classRequest = new ClassRequest(getApplicationContext());

        Intent receivedIntent = getIntent();
        classId = receivedIntent.getIntExtra(BUNDLE_PARM_CLASS_ID, -1);

        view = new ViewForInquiryActivity(getApplicationContext());
        setContentView(view.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inquiry,menu);
        mi_inquiry = menu.findItem(R.id.action_inquiry);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_inquiry) {
            requestInquiry();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestInquiry()
    {
        prepareUIForRequest();
        try {
            this.classRequest.inquire(classId, view.getInquiryText(), this);
        } catch (JSONException e) {
            AppTerminator.error(this,"ClassRequest.inquire : " + e.toString());
        }
    }

    private void prepareUIForRequest()
    {
        mi_inquiry.setVisible(false);
        setSupportProgressBarIndeterminateVisibility(true);
        view.lockAllEditTexts();
    }

    private void releaseUIAfterRequest()
    {
        mi_inquiry.setVisible(true);
        setSupportProgressBarIndeterminateVisibility(false);
        view.releaseAllEditTexts();
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        releaseUIAfterRequest();
        Toast.makeText(getApplicationContext(),"정상적으로 문의가 접수되었습니다.\n최대한 빠르게 연락드리겠습니다.\n감사합니다.",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        releaseUIAfterRequest();
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            Toast.makeText(this, getResources().getString(R.string.network_check_alert), Toast.LENGTH_LONG).show();
        }
        else
        {
            AppTerminator.error(this, "classRequest.inquire fail : " + errorCode);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME+"/"+classId);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }
}
