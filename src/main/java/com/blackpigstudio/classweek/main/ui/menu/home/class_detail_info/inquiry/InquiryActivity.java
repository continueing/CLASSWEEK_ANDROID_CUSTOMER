package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.inquiry;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.singn_in_up.SignInAndUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class InquiryActivity extends ActionBarActivity implements HttpRequester.NetworkResponseListener {
    public static final String BUNDLE_PARM_CLASS_ID = "classes_id";
    public static final String BUNDLE_PARM_SCHEDULE_ID = "schedule_id";

    private MenuItem mi_inquiry;
    private ViewForInquiryActivity view;
    private ClassRequest classRequest;
    private UserPreference userPreference;
    private int classId;
    private int scheduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPreference = new UserPreference(getApplicationContext());
        classRequest = new ClassRequest();

        Intent intent = getIntent();
        classId = intent.getIntExtra(BUNDLE_PARM_CLASS_ID, -1);
        scheduleId = intent.getIntExtra(BUNDLE_PARM_SCHEDULE_ID, -1);

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
            if(this.userPreference.isLoggedIn()) {
                requestInquiry();
            }
            else {
                Intent intent = new Intent(this, SignInAndUpActivity.class);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestInquiry()
    {
        prepareUIForRequest();
        try {
            this.classRequest.inquire(classId, scheduleId, view.getInquiryText(), this);
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
    }
}
