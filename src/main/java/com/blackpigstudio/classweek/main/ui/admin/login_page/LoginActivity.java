package com.blackpigstudio.classweek.main.ui.admin.login_page;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.network.UserRequest;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.admin.sign_up_page.ViewForSignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends ActionBarActivity {
    public final static int REQUEST_FOR_ACTIVITY_RESULT = 0;
    public final static String JSON_KEY_NAME = "name";
    public final static String JSON_KEY_GENDER = "gender";
    public final static String JSON_KEY_BIRTHDAY = "birthday";
    public final static String JSON_KEY_PHONE_NUMBER = "phonenumber";

    ViewForLoginActivity view;
    private MenuItem mi_submitLogin;
    private UserRequest userRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getActionBar().setIcon(android.R.color.transparent);
        this.userRequest = new UserRequest(getApplicationContext());
        view = new ViewForLoginActivity(getApplicationContext());
        setContentView(view.getRoot());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in_and_up, menu);
        mi_submitLogin = menu.findItem(R.id.action_sign_in_and_up);
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
            AppTerminator.error(LoginActivity.this, "requestLogin()- userRequest.login(view.getEmail(),view.getPassword(),this): " + e.toString());
        }
    }

    HttpRequester.NetworkResponseListener loginListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            UserPreference userPreference = new UserPreference(getApplicationContext());
            userPreference.login(view.getEmail(),view.getPassword());
            storeUserData(jsonObject, userPreference);

            Toast.makeText(getApplicationContext(), "로그인 하셨습니다.", Toast.LENGTH_LONG).show();
            finish();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            restoreUIAfterRequest();
            if(errorCode == JsonResponseHandler.ERROR_CODE_PASSWORD_INCORRECT)
            {
                Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
            }
            else if(errorCode == JsonResponseHandler.ERROR_CODE_ID_NOT_EXIST)
            {
                Toast.makeText(getApplicationContext(),"아이디가 존재하지 않습니다.",Toast.LENGTH_LONG).show();
            }
            else
            {
                AppTerminator.error(LoginActivity.this,"userRequset.login fail " + errorCode);
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


    private void prepareUIForRequest()
    {
        mi_submitLogin.setVisible(false);
        setSupportProgressBarIndeterminateVisibility(true);
        view.lockAllEditTexts();
    }

    private void restoreUIAfterRequest()
    {
        setSupportProgressBarIndeterminateVisibility(false);
        mi_submitLogin.setVisible(true);
        view.releaseAllEditTexts();
    }

}
