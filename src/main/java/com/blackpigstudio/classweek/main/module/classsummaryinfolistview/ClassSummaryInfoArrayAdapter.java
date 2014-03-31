package com.blackpigstudio.classweek.main.module.classsummaryinfolistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ClassSummaryInfoArrayAdapter extends ArrayAdapter<ClassSummaryInfoLayout.IClassSummaryInfo> {

    public ClassSummaryInfoArrayAdapter(Context context, int resource) {
        super(context, resource);
    }



//    public ClassSummaryInfoArrayAdapter(Context context, int resource) {
//        super(context, resource);
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassSummaryInfoLayout classSummaryInfoLayout = (ClassSummaryInfoLayout)convertView;

        if(classSummaryInfoLayout == null)
            classSummaryInfoLayout = new ClassSummaryInfoLayout(getContext());

        classSummaryInfoLayout.setData(getItem(position));
        return classSummaryInfoLayout;
    }

}
