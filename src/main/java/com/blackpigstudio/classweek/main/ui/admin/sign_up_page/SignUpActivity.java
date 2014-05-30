package com.blackpigstudio.classweek.main.ui.admin.sign_up_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.etc.EventOfGoogleAnalytics;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.network.UserRequest;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends ActionBarActivity  {
    public final static int REQUEST_FOR_ACTIVITY_RESULT = 0;
    public final static String  SCREEN_NAME = "signInAndUpActivity";
    public final static String JSON_KEY_NAME = "name";
    public final static String JSON_KEY_GENDER = "gender";
    public final static String JSON_KEY_BIRTHDAY = "birthday";
    public final static String JSON_KEY_PHONE_NUMBER = "phonenumber";

    private MenuItem mi_submitSignInAndUp;
    private UserRequest userRequest;
    private ViewForSignUpActivity view;
    private EasyTracker easyTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userRequest = new UserRequest(getApplicationContext());
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getActionBar().setIcon(android.R.color.transparent);
        view = new ViewForSignUpActivity(getApplicationContext());
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
            requestSignUp();
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
            AppTerminator.error(SignUpActivity.this, "requestLogin()- userRequest.login(view.getEmail(),view.getPassword(),this): " + e.toString());
        }
    }

    private void requestSignUp()
    {
        easyTracker.send(
                MapBuilder.createEvent(
                        EventOfGoogleAnalytics.CATEGORY_ACQUISITION,
                        EventOfGoogleAnalytics.ACTION_SIGN_UP,
                        "" ,
                        (long)0
                ).build()
        );
        prepareUIForRequest();
        try {
            this.userRequest.signUp(view.getEmail(), view.getPassword(), view.getPasswordConfirm(), signUpListener);
        } catch (JSONException e) {
            AppTerminator.error(SignUpActivity.this, "requestSignUp()-userRequest.signUp(view.getEmail(),view.getPassword(), view.getPasswordConfirm(), this): " + e.toString());
        }
    }


    HttpRequester.NetworkResponseListener loginListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            UserPreference userPreference = new UserPreference(getApplicationContext());
            userPreference.login(view.getEmail(),view.getPassword());
            storeUserData(jsonObject, userPreference);

            Toast.makeText(getApplicationContext(),"로그인 하셨습니다.",Toast.LENGTH_LONG).show();
            finish();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            restoreUIAfterRequest();
            if(errorCode == JsonResponseHandler.ERROR_CODE_PASSWORD_INCORRECT)
            {
                Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
            }
            else if(errorCode == JsonResponseHandler.ERROR_CODE_ID_NOT_EXIST)// if there's no this account, should signup.
            {
                prepareUIForRequest();
                requestSignUp();
            }
            else
            {
                AppTerminator.error(SignUpActivity.this,"userRequset.login fail " + errorCode);
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
            Toast.makeText(getApplicationContext(),view.getEmail() + "로 회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
            requestLogin();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            restoreUIAfterRequest();
            if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
                Toast.makeText(SignUpActivity.this,getResources().getString(R.string.network_check_alert),Toast.LENGTH_LONG).show();
            }
            else if(errorCode == JsonResponseHandler.ERROR_CODE_PASSWORDS_ARE_NOT_IDENTICAL)
            {
                Toast.makeText(getApplicationContext(),"패스워드를 일치시켜 주세요.^^",Toast.LENGTH_LONG).show();
            }
            else if(errorCode == JsonResponseHandler.ERROR_CODE_MISSING_USERNAME)
            {
                Toast.makeText(getApplicationContext(),"가입하실 이메일을 입력해 주세요.^^",Toast.LENGTH_LONG).show();
            }
            else if(errorCode == JsonResponseHandler.ERROR_CODE_ALREADY_EXIST_USERNAME)
            {
                Toast.makeText(getApplicationContext(),"이미 가입된 이메일 주소입니다.",Toast.LENGTH_LONG).show();
            }
            else
            {
                AppTerminator.error(this, "userRequest.signup fail : " + errorCode);
            }

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


    @Override
    public void onStart() {
        super.onStart();
        easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME);
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
