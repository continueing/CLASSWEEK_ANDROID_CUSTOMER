package com.blackpigstudio.classweek.main.module.activity_and_fragment;

import android.content.Context;
import android.view.View;

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
        initViews();
        setEvent();
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
