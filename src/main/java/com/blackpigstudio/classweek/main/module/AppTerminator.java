package com.blackpigstudio.classweek.main.module;

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

    public static void errorWithToast(Object anObject, String aMessage, Context anContext)
    {
        Toast.makeText(anContext,aMessage,Toast.LENGTH_LONG).show();
        Log.e(anObject.getClass().getCanonicalName(), aMessage);
        System.exit(-1);
    }
}
