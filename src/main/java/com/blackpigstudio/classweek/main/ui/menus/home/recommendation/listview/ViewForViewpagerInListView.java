package com.blackpigstudio.classweek.main.ui.menus.home.recommendation.listview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.blackpigstudio.classweek.R;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 1..
 */
public class ViewForViewpagerInListView extends LinearLayout {
    public ViewForViewpagerInListView(Context context) {
        this(context, null);
    }

    public ViewForViewpagerInListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView()
    {
        View root = inflate(getContext(), R.layout.item_recommendation_view_pager,this);
        ViewPager viewPager = (ViewPager)root.findViewById(R.id.vp_class_recommendation_images);
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://www.maastrichtuniversity.nl/upload_mm/2/3/9/3675823_UM%20Sport%202013%20-%20jump-6_830x455.jpg");
        urls.add("http://www.maastrichtuniversity.nl/upload_mm/2/3/9/3675823_UM%20Sport%202013%20-%20jump-6_830x455.jpg");
        urls.add("http://www.maastrichtuniversity.nl/upload_mm/2/3/9/3675823_UM%20Sport%202013%20-%20jump-6_830x455.jpg");
        RecommendationViewPagerAdapter recommendationViewPagerAdapter = new RecommendationViewPagerAdapter(urls,getContext());
        viewPager.setAdapter(recommendationViewPagerAdapter);
    }
}
