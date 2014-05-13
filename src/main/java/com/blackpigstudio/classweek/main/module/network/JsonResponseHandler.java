package com.blackpigstudio.classweek.main.module.network;

import android.util.Log;

import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class JsonResponseHandler extends JsonHttpResponseHandler {
    private HttpRequester.NetworkResponseListener networkResponseListener;
    private static final String PARM_RESULT = "result";
    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_FAIL = "fail";
    private static final String PARM_ERROR_CODE = "error_code";
    public static final String PARM_DATA = "data";
    public static final int ERROR_CODE_NETWORK_UNAVAILABLE = -1;
    public static final int ERROR_CODE_PASSWORDS_ARE_NOT_IDENTICAL = 1;
    public static final int ERROR_CODE_MISSING_USERNAME = 2;
    public static final int ERROR_CODE_ALREADY_EXIST_USERNAME = 3;
    public static final int ERROR_CODE_ID_NOT_EXIST = 4;
    public static final int ERROR_CODE_PASSWORD_INCORRECT = 5;
    public static final int ERROR_CODE_HAVE_TO_LOGIN = 7;

    public JsonResponseHandler(HttpRequester.NetworkResponseListener aNetworkResponseListener)
    {
        this.networkResponseListener = aNetworkResponseListener;
    }

    @Override
    public void onSuccess(JSONObject response) {
        Log.i("JsonResponseHandler",""+response.toString());
        try {
            if(response.getString(PARM_RESULT).equals(RESULT_SUCCESS))
                this.networkResponseListener.onSuccess(response);
            else if(response.getString(PARM_RESULT).equals(RESULT_FAIL))
                this.networkResponseListener.onFail(response,response.getInt(PARM_ERROR_CODE));
        } catch (JSONException e) {
            AppTerminator.error(this, "onSuccess: while parsing json response - " + e.toString());
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        //TODO: handle network not enable
        if(statusCode == 0)
        {
            this.networkResponseListener.onFail(new JSONObject(), ERROR_CODE_NETWORK_UNAVAILABLE);
        }
        else {
            AppTerminator.error(this, "status code :" + statusCode);
        }
    }
}
