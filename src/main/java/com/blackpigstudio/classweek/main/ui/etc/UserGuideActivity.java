package com.blackpigstudio.classweek.main.ui.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.widget.button.ViewPagerIndexer;
import com.blackpigstudio.classweek.main.ui.admin.front_page.FrontActivity;

public class UserGuideActivity extends Activity {
    private ImageView iv_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);
        iv_close = (ImageView)findViewById(R.id.iv_user_guide_close);

        ViewPager viewPager = (ViewPager)findViewById(R.id.vp_user_guide_images);
        UserGuideImagesViewPagerAdapter viewPagerAdapter = new UserGuideImagesViewPagerAdapter(getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);
        ViewPagerIndexer viewPagerIndexer = (ViewPagerIndexer)findViewById(R.id.vpi_user_guide_images);
        viewPagerIndexer.init(viewPager,viewPagerAdapter,R.drawable.navi_coach_dim, R.drawable.navi_coach_nor ,17);
        viewPagerIndexer.setOnPageChangerListener(onPageChangeListener);

        iv_close.setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, FrontActivity.class );
        i.putExtra(FrontActivity.BUNDLE_PARM_IS_FIRST_ENTRANCE, true);
        startActivity(i);
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent( UserGuideActivity.this, FrontActivity.class );
            i.putExtra(FrontActivity.BUNDLE_PARM_IS_FIRST_ENTRANCE, true);
            startActivity(i);
            finish();
        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position == 2)
            {
                iv_close.setVisibility(View.VISIBLE);
            }
            else
            {
                iv_close.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


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
