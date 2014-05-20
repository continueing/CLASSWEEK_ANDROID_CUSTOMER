package com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 3. 26..
 */
public class HomeSpinnerAdapter implements SpinnerAdapter {
    private LayoutInflater inflater;
    public static final String[] categories= {"추천 클래스","댄스","음악"};
    private static HomeSpinnerAdapter instance;

    public static HomeSpinnerAdapter getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new HomeSpinnerAdapter(context);
        }
        return instance;
    }


    private HomeSpinnerAdapter(Context context)
    {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View root = (View)this.inflater.inflate(R.layout.home_spinner_item, null);
        TextView tv_home_spinner_item = (TextView)root.findViewById(R.id.tv_home_spinner_item);
        tv_home_spinner_item.setText(categories[i]);
        return root;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int i) {
        return categories[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root = (View)this.inflater.inflate(R.layout.home_spinner_item, null);
        TextView tv_home_spinner_item = (TextView) root.findViewById(R.id.tv_home_spinner_item);
        tv_home_spinner_item.setText(categories[i]);
        return root;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
