package com.blackpigstudio.classweek.main.module.activity_and_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by continueing on 2014. 4. 1..
 * Responsibility: View of MVC. Kind of base class for concrete View class of Fragment.
 * This View(MVC) has inflated views(Android) initially. Don't have to inflate additionally.
 */


public abstract class AbstractViewForFragment {
    private Context context;
    private View root;

    public AbstractViewForFragment(Context context, LayoutInflater layoutInflater, ViewGroup container)
    {
        this.context = context;
        root = inflate(layoutInflater, container);
        initViews();
        setEvents();
    }

    abstract protected View inflate(LayoutInflater inflater, ViewGroup container);
    abstract protected void initViews();
    abstract protected void setEvents();

    protected View findViewById(int aResourceId)
    {
        return root.findViewById(aResourceId);
    }

    public Context getContext(){return this.context;}
    public View getRoot(){return root;}
}
