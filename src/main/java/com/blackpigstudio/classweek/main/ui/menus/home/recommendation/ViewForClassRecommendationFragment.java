package com.blackpigstudio.classweek.main.ui.menus.home.recommendation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.templatemethodview.AbstractViewForFragment;
import com.blackpigstudio.classweek.main.ui.menus.home.recommendation.listview.ArrayAdapterForClassSummaryInfoListViewWithImageViewPager;

/**
 * Created by continueing on 2014. 3. 26..
 */

public class ViewForClassRecommendationFragment extends AbstractViewForFragment {


    public ViewForClassRecommendationFragment(Context context, LayoutInflater layoutInflater, ViewGroup container) {
        super(context, layoutInflater, container);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_class_recommendation, container, false);
    }

    @Override
    protected void initViews() {
        ArrayAdapterForClassSummaryInfoListViewWithImageViewPager arrayAdapterForClassSummaryInfoListViewWithImageViewPager = new ArrayAdapterForClassSummaryInfoListViewWithImageViewPager(getContext(),R.layout.item_class_summary_information);
        for(int i = 0 ; i< 3 ;i++)
        {
            arrayAdapterForClassSummaryInfoListViewWithImageViewPager.add(new ClassSummaryInfo(i));
        }

        ListView lv_class_summary_info = (ListView)findViewById(R.id.lv_recommendation_class_summary_info);
        lv_class_summary_info.setAdapter(arrayAdapterForClassSummaryInfoListViewWithImageViewPager);


    }

    @Override
    protected void setEvents(){}


}
