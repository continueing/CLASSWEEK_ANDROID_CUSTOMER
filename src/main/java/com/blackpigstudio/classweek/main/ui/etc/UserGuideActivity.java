package com.blackpigstudio.classweek.main.ui.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.widget.button.ViewPagerIndexer;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

public class UserGuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);

        ViewPager viewPager = (ViewPager)findViewById(R.id.vp_user_guide_images);
        UserGuideImagesViewPagerAdapter viewPagerAdapter = new UserGuideImagesViewPagerAdapter(getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);
        ViewPagerIndexer viewPagerIndexer = (ViewPagerIndexer)findViewById(R.id.vpi_user_guide_images);
        viewPagerIndexer.init(viewPager,viewPagerAdapter,R.drawable.navi_coach_dim, R.drawable.navi_coach_nor ,17);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent( this, MainActivity.class );
        startActivity(i);
    }


    public static class UserGuideImagesViewPagerAdapter extends PagerAdapter {
        private Context context;
        private static final int NUM_OF_GUIDE_PAGES =3;

        public UserGuideImagesViewPagerAdapter(Context aContext) {
            this.context = aContext;
        }

        @Override
        public int getCount() {
            return NUM_OF_GUIDE_PAGES;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(this.context);
            if(position == 0)
                imageView.setImageResource(R.drawable.img_coachmark_01);
            else if(position == 1)
                imageView.setImageResource(R.drawable.img_coachmark_02);
            else if(position == 2)
                imageView.setImageResource(R.drawable.img_coachmark_03);

            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ((ViewPager)container).addView(imageView, ((ViewPager)container).getChildCount() > position ? position : ((ViewPager)container).getChildCount());
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView)object);
        }
    }

}
