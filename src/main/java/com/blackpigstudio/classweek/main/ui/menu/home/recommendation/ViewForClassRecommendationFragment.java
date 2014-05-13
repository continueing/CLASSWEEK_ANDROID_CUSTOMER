package com.blackpigstudio.classweek.main.ui.menu.home.recommendation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForFragment;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummaryInfoChooseListener;
import com.blackpigstudio.classweek.main.ui.menu.home.recommendation.listview.ArrayAdapterForClassSummaryInfoListViewWithImageViewPager;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 3. 26..
 */

public class ViewForClassRecommendationFragment extends AbstractViewForFragment {
    private ProgressbarFooter progressbarFooter;
    private LayoutInflater layoutInflater;
    private ListView lv_class_summary_info;
    private ArrayAdapterForClassSummaryInfoListViewWithImageViewPager arrayAdapterForClassSummaryInfoListViewWithImageViewPager;
    OnClassSummaryInfoChooseListener onClassSummaryInfoChooseListener;

    public ViewForClassRecommendationFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, OnClassSummaryInfoChooseListener anOnClassSummaryInfoChooseListener) {
        super(context, layoutInflater, container);
        this.onClassSummaryInfoChooseListener = anOnClassSummaryInfoChooseListener;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        layoutInflater = inflater;
        return layoutInflater.inflate(R.layout.fragment_class_recommendation, container, false);
    }

    @Override
    protected void initViews() {
        arrayAdapterForClassSummaryInfoListViewWithImageViewPager = new ArrayAdapterForClassSummaryInfoListViewWithImageViewPager(getContext(),R.layout.item_class_summary_information);
        lv_class_summary_info = (ListView)findViewById(R.id.lv_recommendation_class_summary_info);
        progressbarFooter = new ProgressbarFooter(lv_class_summary_info,layoutInflater);
        setProgressbarVisibility(true);
        lv_class_summary_info.setAdapter(arrayAdapterForClassSummaryInfoListViewWithImageViewPager);
    }

    public void setData(ArrayList<IClassSummaryInfoItem> iClassSummaryInfoItems, ArrayList<String> anUrls)
    {
        this.arrayAdapterForClassSummaryInfoListViewWithImageViewPager.addAll(iClassSummaryInfoItems);
        this.arrayAdapterForClassSummaryInfoListViewWithImageViewPager.setData(anUrls);
        setProgressbarVisibility(false);
    }

    @Override
    protected void setEvents()
    {
        lv_class_summary_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClassSummaryInfoChooseListener.onClassSummaryInfoChoose(arrayAdapterForClassSummaryInfoListViewWithImageViewPager.getItem(i - 1));
            }
        });
    }

    public void setProgressbarVisibility(boolean aVisibility)
    {
        this.progressbarFooter.setVisibility(aVisibility);
    }


}
