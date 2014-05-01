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

import org.json.JSONException;
import org.json.JSONObject;

public class InquiryActivity extends ActionBarActivity implements HttpRequester.NetworkResponseListener {
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
        finish();
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        releaseUIAfterRequest();
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            Toast.makeText(this, "인터넷 연결을 확인해 주세요", Toast.LENGTH_LONG).show();
        }
        else
        {
            AppTerminator.error(this, "classRequest.inquire fail : " + errorCode);
        }
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
