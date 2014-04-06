package com.blackpigstudio.classweek.main.module.network;

import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 6..
 */
public class HttpRequester {

    public static void foo(final NetworkResponseListener networkResponseListener, String url)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this)
                {
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                networkResponseListener.onSuccess(new JSONObject());
            }
        });

        thread.start();
    }

    public static interface NetworkResponseListener
    {
        public void onSuccess(JSONObject jsonObject);

        public void onFail(JSONObject jsonObject, int errorCode);

    }
}
