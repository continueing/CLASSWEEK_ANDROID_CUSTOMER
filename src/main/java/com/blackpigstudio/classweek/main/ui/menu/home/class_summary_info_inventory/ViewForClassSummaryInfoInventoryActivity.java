package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.ArrayAdapterForClassSummaryInfoListView;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummeryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.ViewForClassSummaryInfoListViewItem;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 2..
 */
public class ViewForClassSummaryInfoInventoryActivity extends AbstractViewForActivity{
    private ArrayAdapterForClassSummaryInfoListView arrayAdapterForClassSummaryInfoListView;
    private OnClassSummeryInfoChooseListener onClassSummeryInfoChooseListener;
    private OnScrollOfListViewListener onScrollOfListViewListener;
    private ProgressbarFooter progressbarFooter;
    ListView lv_class_summary_info;


    public ViewForClassSummaryInfoInventoryActivity(Context context, OnClassSummeryInfoChooseListener onClassSummeryInfoChooseListener, OnScrollOfListViewListener onScrollOfListViewListener) {
        super(context);
        this.onClassSummeryInfoChooseListener = onClassSummeryInfoChooseListener;
        this.onScrollOfListViewListener = onScrollOfListViewListener;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_class_summary_info_inventory, null);
    }

    @Override
    protected void initViews() {
        lv_class_summary_info = (ListView)findViewById(R.id.lv_class_summary_info);
        this.progressbarFooter = new ProgressbarFooter(lv_class_summary_info,LayoutInflater.from(getContext()));
        arrayAdapterForClassSummaryInfoListView = new ArrayAdapterForClassSummaryInfoListView(getContext(),R.layout.item_class_summary_information);
        setProgressbarVisibility(true); // should be placed before set adapter
        lv_class_summary_info.setAdapter(arrayAdapterForClassSummaryInfoListView);

    }

    @Override
    protected void setEvent() {
        lv_class_summary_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClassSummeryInfoChooseListener.onClassSummeryInfoChoose(((ViewForClassSummaryInfoListViewItem)view).getIClassSummaryInfoItem());
            }
        });

        lv_class_summary_info.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean visibilityOfLastItem = false;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int aScrollState) {
                if(aScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    if(aScrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibilityOfLastItem)
                    {
                        onScrollOfListViewListener.atScrollIsOnEndItem();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int aFirstVisibleItem, int aVisibleItemCount, int aTotalItemCount)
            {
                visibilityOfLastItem = aFirstVisibleItem + aVisibleItemCount >= aTotalItemCount;
            }
        });
    }

    public void setProgressbarVisibility(boolean aVisibility)
    {
        this.progressbarFooter.setVisibility(aVisibility);
    }

    public void addClassSummaryInfoItemArrayList(ArrayList<IClassSummaryInfoItem> iClassSummaryInfoItems)
    {
        this.arrayAdapterForClassSummaryInfoListView.addAll(iClassSummaryInfoItems);
    }

    public void clearListViewItems()
    {
        this.arrayAdapterForClassSummaryInfoListView.clear();
    }

}
