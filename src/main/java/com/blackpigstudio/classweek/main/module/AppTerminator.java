package com.blackpigstudio.classweek.main.module;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by continueing on 2014. 4. 11..
 */
public class AppTerminator {

    public static void error(Object anObject, String aMessage)
    {
        Log.e(anObject.getClass().getCanonicalName(), aMessage);
        System.exit(-1);
    }

    public static void finishActivityWithToast(String aMessage, Activity anActivity)
    {
        Toast.makeText(anActivity,aMessage,Toast.LENGTH_LONG).show();
        anActivity.finish();
    }
}
