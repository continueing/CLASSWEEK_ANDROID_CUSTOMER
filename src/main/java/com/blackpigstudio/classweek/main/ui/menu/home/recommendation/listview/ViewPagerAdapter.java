package com.blackpigstudio.classweek.main.ui.menu.home.recommendation.listview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 1..
 */
public class ViewPagerAdapter extends PagerAdapter{
    private ArrayList<String> urls;
    private Context context;

    public ViewPagerAdapter(ArrayList<String> aUrls, Context context)
    {
        this.urls = aUrls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((SmartImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SmartImageView smartImageView = new SmartImageView(this.context);
        smartImageView.setImageUrl(urls.get(position));
        smartImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ((ViewPager)container).addView(smartImageView, ((ViewPager) container).getChildCount() > position ? position : ((ViewPager) container).getChildCount());
        return smartImageView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }
}
