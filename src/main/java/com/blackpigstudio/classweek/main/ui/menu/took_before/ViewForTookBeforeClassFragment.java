package com.blackpigstudio.classweek.main.ui.menu.took_before;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.MyClass;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForFragment;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class ViewForTookBeforeClassFragment extends AbstractViewForFragment{
    private ListView lv_tookBeforeClass;


    public ViewForTookBeforeClassFragment(Context context, LayoutInflater layoutInflater, ViewGroup container) {
        super(context, layoutInflater, container);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_took_before_class, container, false);
    }

    @Override
    protected void initViews() {
        this.lv_tookBeforeClass = (ListView)findViewById(R.id.lv_took_before_class);
        ArrayAdapterForTookBeforeClassListView arrayAdapterForTookBeforeClassListView = new ArrayAdapterForTookBeforeClassListView(getContext(),R.layout.item_took_before_class);
        this.lv_tookBeforeClass.setAdapter(arrayAdapterForTookBeforeClassListView);
//        arrayAdapterForTookBeforeClassListView.add(new MyClass());
//        arrayAdapterForTookBeforeClassListView.add(new MyClass());
    }

    @Override
    protected void setEvents() {

    }
}
