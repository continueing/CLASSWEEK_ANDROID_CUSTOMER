package com.blackpigstudio.classweek.main.ui.singn_in_up;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.UserRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInAndUpActivity extends ActionBarActivity  {
    public final static String INTENT_PARM_LOGIN_RESULT = "intent_parm_login_result";
    private MenuItem mi_submitSignInAndUp;
    private ViewForSignInAndUpActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        view = new ViewForSignInAndUpActivity(getApplicationContext());
        setContentView(view.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in_and_up, menu);
        mi_submitSignInAndUp = menu.findItem(R.id.action_sign_in_and_up);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sign_in_and_up) {
            requestLogin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestLogin()
    {
        prepareUIForRequest();
        UserRequest userRequest = new UserRequest();
        try {
            userRequest.login(view.getEmail(),view.getPassword(),loginListener);
        } catch (JSONException e) {
            AppTerminator.error(SignInAndUpActivity.this, "requestLogin()- userRequest.login(view.getEmail(),view.getPassword(),this): " + e.toString());
        }
    }

    private void requestSignUp()
    {
        prepareUIForRequest();
        UserRequest userRequest = new UserRequest();
        try {
            userRequest.signUp(view.getEmail(), view.getPassword(), view.getPasswordConfirm(), signUpListener);
        } catch (JSONException e) {
            AppTerminator.error(SignInAndUpActivity.this, "requestSignUp()-userRequest.signUp(view.getEmail(),view.getPassword(), view.getPasswordConfirm(), this): " + e.toString());
        }
    }


    HttpRequester.NetworkResponseListener loginListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            // should store preference

            Intent intent = new Intent();
            intent.putExtra(INTENT_PARM_LOGIN_RESULT,true);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            restoreUIAfterRequest();

            if(true)// if theres no id
            {
                prepareUIForRequest();
                requestSignUp();
            }
        }
    };


    HttpRequester.NetworkResponseListener signUpListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject)
        {
            restoreUIAfterRequest();
            requestLogin();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            restoreUIAfterRequest();
        }
    };


    private void prepareUIForRequest()
    {
        mi_submitSignInAndUp.setVisible(false);
        setSupportProgressBarIndeterminateVisibility(true);
        view.lockAllEditTexts();
    }

    private void restoreUIAfterRequest()
    {
        setSupportProgressBarIndeterminateVisibility(false);
        mi_submitSignInAndUp.setVisible(true);
        view.releaseAllEditTexts();
    }
}
