package com.blackpigstudio.classweek.main.module;

import android.util.Log;



/**
 * Created by continueing on 2014. 4. 11..
 */
public class AppTerminator {

    public static void error(Object anObject, String aMessage)
    {
        Log.e(anObject.getClass().getCanonicalName(), aMessage);
        System.exit(-1);
    }
}
