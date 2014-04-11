package com.blackpigstudio.classweek.main.module.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 4. 8..
 */
public class ProgressbarFooter {
    private View footer;
    private ListView listView;
    private boolean visibility;

    public ProgressbarFooter(ListView aListView, LayoutInflater aInflater)
    {
        this(aListView, aInflater.inflate(R.layout.footer_progressbar,null,false));
    }

    public ProgressbarFooter(ListView aListView, View aFooterView)
    {
        this.listView = aListView;
        this.footer = aFooterView;
        this.visibility = false;
    }

    public boolean getVisibility()
    {
        return this.visibility;
    }

    public void setVisibility(boolean aVisibility)
    {
        this.visibility = aVisibility;
        if(aVisibility)
        {
            this.listView.addFooterView(this.footer,null,false);
        }
        else
        {
            this.listView.removeFooterView(this.footer);
        }
    }



}
