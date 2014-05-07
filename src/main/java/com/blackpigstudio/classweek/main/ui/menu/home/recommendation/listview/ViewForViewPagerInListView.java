package com.blackpigstudio.classweek.main.ui.menu.home.recommendation.listview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.widget.button.ViewPagerIndexer;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 1..
 */
public class ViewForViewPagerInListView extends LinearLayout {
    ViewPager viewPager;
    View root;
    ViewPagerIndexer viewPagerIndexer;

    public ViewForViewPagerInListView(Context context) {
        this(context, null);
    }

    public ViewForViewPagerInListView(Context context, AttributeSet attrs ) {
        super(context, attrs);
        initView();
    }

    private void initView()
    {
        root = inflate(getContext(), R.layout.item_recommendation_view_pager,this);
        viewPager = (ViewPager)root.findViewById(R.id.vp_class_recommendation_images);
        viewPagerIndexer = (ViewPagerIndexer)root.findViewById(R.id.vpi_class_recommendation_images);
    }

    public void setData(ArrayList<String> anUrls)
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(anUrls,getContext());
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerIndexer.init(viewPager,viewPagerAdapter);
    }
}
