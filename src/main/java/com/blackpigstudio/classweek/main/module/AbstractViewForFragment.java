package com.blackpigstudio.classweek.main.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by continueing on 2014. 4. 1..
 * Responsibility: V of MVC. Kind of base class for concrete View Classes.
 */


public abstract class AbstractViewForFragment {
    private Context context;
    private View root;

    public AbstractViewForFragment(Context context, LayoutInflater layoutInflater, ViewGroup container)
    {
        this.context = context;
        root = inflate(layoutInflater, container);
        initView();
    }

    abstract protected View inflate(LayoutInflater inflater, ViewGroup container);
    abstract protected void initView();

    protected View findViewById(int aResourceId)
    {
        return root.findViewById(aResourceId);
    }
    public Context getContext(){return this.context;}
    public View getRoot(){return root;}
}
