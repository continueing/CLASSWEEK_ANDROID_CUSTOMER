package com.blackpigstudio.classweek.main.ui.menus.home.class_summary_info_inventory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.classsummaryinfolistview.ArrayAdapterForClassSummaryInfoListView;
import com.blackpigstudio.classweek.main.module.classsummaryinfolistview.ViewForClassSummaryInfoListViewItem;
import com.blackpigstudio.classweek.main.module.templatemethodview.AbstractViewForActivity;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 2..
 */
public class ViewForClassSummeryInfoInventoryActivity extends AbstractViewForActivity{
    private ArrayAdapterForClassSummaryInfoListView arrayAdapterForClassSummaryInfoListView;
    private OnClassSummeryInfoChooseListener onClassSummeryInfoChooseListener;
    ListView lv_class_summary_info;


    public ViewForClassSummeryInfoInventoryActivity(Context context, OnClassSummeryInfoChooseListener onClassSummeryInfoChooseListener) {
        super(context);
        this.onClassSummeryInfoChooseListener = onClassSummeryInfoChooseListener;
        initViews();
        setEvent();
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_class_summery_info_inventory, null);
    }

    @SuppressLint("NewApi")
    @Override
    protected void initViews() {
        lv_class_summary_info = (ListView)findViewById(R.id.lv_class_summary_info);
        arrayAdapterForClassSummaryInfoListView = new ArrayAdapterForClassSummaryInfoListView(getContext(),R.layout.item_class_summary_information);
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
    }

    public void addClassSummaryInfoItemArrayList(ArrayList<ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem> iClassSummaryInfoItems)
    {
        this.arrayAdapterForClassSummaryInfoListView.addAll(iClassSummaryInfoItems);
    }


    public static interface OnClassSummeryInfoChooseListener
    {
        public void onClassSummeryInfoChoose(ViewForClassSummaryInfoListViewItem.IClassSummaryInfoItem iClassSummaryInfoItem);
    }
}
