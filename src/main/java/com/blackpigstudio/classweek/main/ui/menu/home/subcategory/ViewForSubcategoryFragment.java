package com.blackpigstudio.classweek.main.ui.menu.home.subcategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForFragment;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class ViewForSubcategoryFragment extends AbstractViewForFragment {
    private OnSubCategoryChooseListener onSubCategoryChooseListener;
    private ListView lv_subcategory;
    private ArrayAdapterForSubcategoryListView arrayAdapterForSubcategoryListView;
    private ProgressbarFooter progressbarFooter;

    public ViewForSubcategoryFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, OnSubCategoryChooseListener onSubCategoryChooseListener) {
        super(context, layoutInflater, container);
        this.onSubCategoryChooseListener = onSubCategoryChooseListener;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_subcategory,container, false);
    }

    @Override
    protected void initViews() {
        lv_subcategory = (ListView)findViewById(R.id.lv_subcategory);
        this.progressbarFooter = new ProgressbarFooter(lv_subcategory,LayoutInflater.from(getContext()));
        arrayAdapterForSubcategoryListView = new ArrayAdapterForSubcategoryListView(getContext(),R.layout.item_subcategory);
        setProgressbarVisibility(true);
        lv_subcategory.setAdapter(arrayAdapterForSubcategoryListView);
    }

    @Override
    protected void setEvents() {
        lv_subcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewForSubcategoryListViewItem.ISubcategory iSubcategory = arrayAdapterForSubcategoryListView.getItem(i);
                onSubCategoryChooseListener.onSubCategoryChoose(iSubcategory.getEngTitle(),iSubcategory.getKorTitle());
            }
        });
    }

    public void setProgressbarVisibility(boolean aVisibility)
    {
        this.progressbarFooter.setVisibility(aVisibility);
    }

    public void addISubcategoryArrayList(ArrayList<ViewForSubcategoryListViewItem.ISubcategory> aISubcategories)
    {
        arrayAdapterForSubcategoryListView.addAll(aISubcategories);
    }

    public interface OnSubCategoryChooseListener
    {
        public void onSubCategoryChoose(String aSubcategoryForUrl, String aSubcategoryForKor);
    }
}
