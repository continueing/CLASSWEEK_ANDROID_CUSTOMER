package com.blackpigstudio.classweek.main.module.templatemethodview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 4. 2..
 */
abstract public class AbstractViewForActivity {

    private View root;
    private Context context;

    public AbstractViewForActivity(Context context)
    {
        this.context = context;
        this.root = inflate();
    }

    public View findViewById(int aResourceId)
    {
        return getRoot().findViewById(aResourceId);
    }

    abstract protected View inflate();
    abstract protected void initViews();
    abstract protected void setEvent();

    public View getRoot(){return this.root;}


    public Context getContext()
    {
        return this.context;
    }
}
