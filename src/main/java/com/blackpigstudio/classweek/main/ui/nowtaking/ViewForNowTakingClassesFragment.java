package com.blackpigstudio.classweek.main.ui.nowtaking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.NowTakingClass;
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
        return inflater.inflate(R.layout.fragment_now_taking_classes, container, false);
    }

    @Override
    protected void initViews() {
        lv_nowTakingClasses = (ListView)findViewById(R.id.lv_now_taking_classes);
        ArrayAdapterForNowTakingClassesListView arrayAdapterForNowTakingClassesListView = new ArrayAdapterForNowTakingClassesListView(getContext(), R.layout.item_now_taking_class);
        lv_nowTakingClasses.setAdapter(arrayAdapterForNowTakingClassesListView);
        arrayAdapterForNowTakingClassesListView.add(new NowTakingClass());
        arrayAdapterForNowTakingClassesListView.add(new NowTakingClass());
        arrayAdapterForNowTakingClassesListView.add(new NowTakingClass());

    }

    @Override
    protected void setEvents() {
    }
}
