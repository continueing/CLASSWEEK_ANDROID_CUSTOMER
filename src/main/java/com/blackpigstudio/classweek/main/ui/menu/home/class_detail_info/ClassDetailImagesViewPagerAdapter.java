package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blackpigstudio.classweek.R;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 23..
 */
public class ClassDetailImagesViewPagerAdapter extends PagerAdapter {
    private ArrayList<String> urls;
    private Context context;

    public ClassDetailImagesViewPagerAdapter(Context aContext, ArrayList<String> aUrls) {
        this.context = aContext;
        this.urls = new ArrayList<String>();
        this.urls.addAll(aUrls);
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
        ((ViewPager)container).addView(smartImageView, ((ViewPager)container).getChildCount() > position ? position : ((ViewPager)container).getChildCount());
        return smartImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }
}
