package com.blackpigstudio.classweek.main.ui.menu.home.recommendation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForFragment;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummeryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.ViewForClassSummaryInfoListViewItem;
import com.blackpigstudio.classweek.main.ui.menu.home.recommendation.listview.ArrayAdapterForClassSummaryInfoListViewWithImageViewPager;

/**
 * Created by continueing on 2014. 3. 26..
 */

public class ViewForClassRecommendationFragment extends AbstractViewForFragment {
    private ProgressbarFooter progressbarFooter;
    private LayoutInflater layoutInflater;
    private ListView lv_class_summary_info;
    private ArrayAdapterForClassSummaryInfoListViewWithImageViewPager arrayAdapterForClassSummaryInfoListViewWithImageViewPager;
    OnClassSummeryInfoChooseListener onClassSummeryInfoChooseListener;

    public ViewForClassRecommendationFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, OnClassSummeryInfoChooseListener anOnClassSummeryInfoChooseListener) {
        super(context, layoutInflater, container);
        this.onClassSummeryInfoChooseListener = anOnClassSummeryInfoChooseListener;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        layoutInflater = inflater;
        return layoutInflater.inflate(R.layout.fragment_class_recommendation, container, false);
    }

    @Override
    protected void initViews() {
        arrayAdapterForClassSummaryInfoListViewWithImageViewPager = new ArrayAdapterForClassSummaryInfoListViewWithImageViewPager(getContext(),R.layout.item_class_summary_information);
        for(int i = 0 ; i< 3 ;i++)
        {
            arrayAdapterForClassSummaryInfoListViewWithImageViewPager.add(new ClassSummaryInfo(i));
        }

        lv_class_summary_info = (ListView)findViewById(R.id.lv_recommendation_class_summary_info);
        progressbarFooter = new ProgressbarFooter(lv_class_summary_info,layoutInflater);
        setProgressbarVisibility(true);
        lv_class_summary_info.setAdapter(arrayAdapterForClassSummaryInfoListViewWithImageViewPager);
    }

    @Override
    protected void setEvents()
    {
        lv_class_summary_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClassSummeryInfoChooseListener.onClassSummeryInfoChoose(arrayAdapterForClassSummaryInfoListViewWithImageViewPager.getItem(i-1));
            }
        });
    }

    public void setProgressbarVisibility(boolean aVisibility)
    {
        this.progressbarFooter.setVisibility(aVisibility);
    }


}
