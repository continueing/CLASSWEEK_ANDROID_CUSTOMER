package com.blackpigstudio.classweek.main.ui.menu.now_taking;

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

public class ViewForNowTakingClassesFragment extends AbstractViewForFragment{
    ListView lv_nowTakingClasses;

    public ViewForNowTakingClassesFragment(Context context, LayoutInflater layoutInflater, ViewGroup container) {
        super(context, layoutInflater, container);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_now_taking_class, container, false);
    }

    @Override
    protected void initViews() {
        lv_nowTakingClasses = (ListView)findViewById(R.id.lv_now_taking_class);
        ArrayAdapterForNowTakingClassListView arrayAdapterForNowTakingClassListView = new ArrayAdapterForNowTakingClassListView(getContext(), R.layout.item_now_taking_class);
        lv_nowTakingClasses.setAdapter(arrayAdapterForNowTakingClassListView);
        arrayAdapterForNowTakingClassListView.add(new MyClass());
        arrayAdapterForNowTakingClassListView.add(new MyClass());
        arrayAdapterForNowTakingClassListView.add(new MyClass());
    }

    @Override
    protected void setEvents() {
    }
}
