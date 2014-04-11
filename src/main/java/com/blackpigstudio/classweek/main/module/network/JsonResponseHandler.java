package com.blackpigstudio.classweek.main.module.network;

import android.util.Log;

import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class JsonResponseHandler extends JsonHttpResponseHandler {
    private HttpRequester.NetworkResponseListener networkResponseListener;
    private final String PARM_RESULT = "result";
    private final String RESULT_SUCCESS = "success";
    private final String RESULT_FAIL = "fail";

    public JsonResponseHandler(HttpRequester.NetworkResponseListener aNetworkResponseListener)
    {
        this.networkResponseListener = aNetworkResponseListener;
    }

    @Override
    public void onSuccess(JSONObject response) {
        Log.e("JsonResponseHandler",""+response.toString());
        try {
            if(response.getString(PARM_RESULT).equals(RESULT_SUCCESS))
                this.networkResponseListener.onSuccess(response);
            else if(response.getString(PARM_RESULT).equals(RESULT_FAIL))
                this.networkResponseListener.onFail(response,0);
        } catch (JSONException e) {
            AppTerminator.error(this, "onSuccess: while parsing json response - " + e.toString());
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        AppTerminator.error(this, "status code :" + statusCode + "\nreponse : " + responseBody.toString());
    }
}
