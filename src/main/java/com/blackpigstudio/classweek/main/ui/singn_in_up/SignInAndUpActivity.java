package com.blackpigstudio.classweek.main.ui.singn_in_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.UserRequest;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInAndUpActivity extends ActionBarActivity  {
    public final static String INTENT_PARM_LOGIN_RESULT = "intent_parm_login_result";
    public final static String JSON_KEY_NAME = "name";
    public final static String JSON_KEY_GENDER = "gender";
    public final static String JSON_KEY_BIRTHDAY = "birthday";
    public final static String JSON_KEY_PHONE_NUMBER = "phonenumber";

    private MenuItem mi_submitSignInAndUp;
    private UserRequest userRequest;
    private ViewForSignInAndUpActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userRequest = new UserRequest(getApplicationContext());
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
        try {
            this.userRequest.login(view.getEmail(),view.getPassword(),loginListener);
        } catch (JSONException e) {
            AppTerminator.error(SignInAndUpActivity.this, "requestLogin()- userRequest.login(view.getEmail(),view.getPassword(),this): " + e.toString());
        }
    }

    private void requestSignUp()
    {
        prepareUIForRequest();
        try {
            this.userRequest.signUp(view.getEmail(), view.getPassword(), view.getPasswordConfirm(), signUpListener);
        } catch (JSONException e) {
            AppTerminator.error(SignInAndUpActivity.this, "requestSignUp()-userRequest.signUp(view.getEmail(),view.getPassword(), view.getPasswordConfirm(), this): " + e.toString());
        }
    }


    HttpRequester.NetworkResponseListener loginListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            UserPreference userPreference = new UserPreference(getApplicationContext());
            userPreference.login(view.getEmail(),view.getPassword());
            storeUserData(jsonObject, userPreference);

            Intent intent = new Intent();
            intent.putExtra(INTENT_PARM_LOGIN_RESULT,true);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            restoreUIAfterRequest();

            if(true)// if there's no this account, should signup.
            {
                prepareUIForRequest();
                requestSignUp();
            }
        }
    };

    private void storeUserData(JSONObject aJsonObject, UserPreference aUserPreference)
    {

        try {
            if(!aJsonObject.isNull(JSON_KEY_NAME))
            {
                aUserPreference.setName(aJsonObject.getString(JSON_KEY_NAME));
            }
            if(!aJsonObject.isNull(JSON_KEY_GENDER))
            {
                String gender = aJsonObject.getString(JSON_KEY_GENDER);
                if(gender.equals('M'))
                {
                    aUserPreference.setSex(aUserPreference.VALUE_SEX_MALE);
                }
                else
                {
                    aUserPreference.setSex(aUserPreference.VALUE_SEX_FEMALE);
                }
            }
            if(!aJsonObject.isNull(JSON_KEY_BIRTHDAY))
            {
                aUserPreference.setBirthDate(aJsonObject.getString(JSON_KEY_BIRTHDAY));
            }
            if(!aJsonObject.isNull(JSON_KEY_PHONE_NUMBER))
            {
                aUserPreference.setPhoneNumber(aJsonObject.getString(JSON_KEY_PHONE_NUMBER));
            }
        } catch (JSONException e) {
            AppTerminator.error(this,e.getMessage());
        }
    }

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
