package com.blackpigstudio.classweek.main.ui.menus.home.recommendation.listview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SmartImageView smartImageView = new SmartImageView(this.context);
        smartImageView.setImageUrl(urls.get(position));
        container.addView(smartImageView,position);
        return smartImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }
}
