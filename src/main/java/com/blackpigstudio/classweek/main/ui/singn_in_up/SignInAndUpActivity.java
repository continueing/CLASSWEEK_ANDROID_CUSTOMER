package com.blackpigstudio.classweek.main.ui.singn_in_up;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;

import org.json.JSONObject;

public class SignInAndUpActivity extends ActionBarActivity implements HttpRequester.NetworkResponseListener {
    private MenuItem mi_submitSignInAndUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        ViewForSignInAndUpActivity viewForSignInAndUpActivity = new ViewForSignInAndUpActivity(getApplicationContext());
        setContentView(viewForSignInAndUpActivity.getRoot());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in_and_up, menu);
        mi_submitSignInAndUp = menu.findItem(R.id.action_sign_in_and_up);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sign_in_and_up) {
            mi_submitSignInAndUp.setVisible(false);
            requestSignInAndUp();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestSignInAndUp()
    {
        HttpRequester.foo(this,"");
        setSupportProgressBarIndeterminateVisibility(true);
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            setSupportProgressBarIndeterminateVisibility(false);
            mi_submitSignInAndUp.setVisible(true);
        }
    };

    @Override
    public void onSuccess(JSONObject jsonObject) {
        handler.sendEmptyMessage(0);
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {

    }
}
