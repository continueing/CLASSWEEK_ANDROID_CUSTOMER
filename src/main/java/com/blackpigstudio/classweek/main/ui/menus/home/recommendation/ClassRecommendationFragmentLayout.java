package com.blackpigstudio.classweek.main.ui.menus.home.recommendation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AbstractFragmentView;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.blackpigstudio.classweek.main.domain.ClassInfo;
import com.blackpigstudio.classweek.main.module.classsummaryinfolistview.ClassSummaryInfoArrayAdapter;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by continueing on 2014. 3. 26..
 */

public class ClassRecommendationFragmentLayout extends AbstractFragmentView{

    public ClassRecommendationFragmentLayout(Context context, LayoutInflater layoutInflater, ViewGroup container) {
        super(context, layoutInflater, container);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_class_recommendation, container, false);
    }

    @Override
    protected void initView() {
        ClassSummaryInfoArrayAdapter classSummaryInfoArrayAdapter = new ClassSummaryInfoArrayAdapter(getContext(),R.layout.item_class_summary_information);
        classSummaryInfoArrayAdapter.add(new ClassInfo(1));
        classSummaryInfoArrayAdapter.add(new ClassInfo(2));
        classSummaryInfoArrayAdapter.add(new ClassInfo(3));
        classSummaryInfoArrayAdapter.add(new ClassInfo(4));

        ListView lv_class_summary_info = (ListView)findViewById(R.id.lv_class_summary_info);
        lv_class_summary_info.setAdapter(classSummaryInfoArrayAdapter);

        ActionBarPullToRefresh.from((MainActivity)getContext()).allChildrenArePullable().listener(onRefreshListener).setup((PullToRefreshLayout)findViewById(R.id.ptr_class_recommendation));
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {

        }
    };
}
