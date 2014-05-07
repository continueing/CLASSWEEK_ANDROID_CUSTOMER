package com.blackpigstudio.classweek.main.module.widget.button;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.ui.menu.home.recommendation.listview.ViewPagerAdapter;

/**
 * Created by continueing on 2014. 5. 7..
 */
public class ViewPagerIndexer extends LinearLayout implements ViewPager.OnPageChangeListener {
    private ImageView []indexs;
    private int length;

    public ViewPagerIndexer(Context context) {
        super(context);
    }

    public ViewPagerIndexer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(ViewPager aViewPager, PagerAdapter aPagerAdapter)
    {
        aViewPager.setOnPageChangeListener(this);
        length = aPagerAdapter.getCount();
        indexs = new ImageView[length];
        for(int i=0 ; i < length;i++ )
        {
            indexs[i] = new ImageView(getContext());
            indexs[i].setImageResource(R.drawable.navi_nor);
            LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if(i != 0)
                param.setMargins(15, 0, 0, 0);
            indexs[i].setLayoutParams(param);
            addView(indexs[i]);
        }
        indexs[0].setImageResource(R.drawable.navi_sel);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0 ; i < length;i++ )
        {
            indexs[i].setImageResource(R.drawable.navi_nor);
        }
        indexs[position].setImageResource(R.drawable.navi_sel);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
