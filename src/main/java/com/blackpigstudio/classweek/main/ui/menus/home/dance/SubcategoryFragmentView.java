package com.blackpigstudio.classweek.main.ui.menus.home.dance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AbstractFragmentView;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 3. 29..
 */
public class SubcategoryFragmentView extends AbstractFragmentView {

    public SubcategoryFragmentView(Context context, LayoutInflater layoutInflater, ViewGroup container) {
        super(context,layoutInflater,container);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_sub_category,container, false);
    }

    @Override
    protected void initView() {
        SmartImageView siv_urban_hiphop = (SmartImageView) findViewById(R.id.siv_dance_subcategory_urban_hiphop);
        SmartImageView siv_kpop = (SmartImageView) findViewById(R.id.siv_dance_subcategory_kpop);
        SmartImageView siv_salsa = (SmartImageView) findViewById(R.id.siv_dance_subcategory_salsa);
        siv_urban_hiphop.setImageUrl("http://farm4.staticflickr.com/3091/2892103441_7d87897f78_o.jpg");
        siv_kpop.setImageUrl("http://farm7.staticflickr.com/6151/6171681106_fe20ce77ff_o.jpg");
        siv_salsa.setImageUrl("http://farm4.staticflickr.com/3284/3035753275_438192b670_o.jpg");
    }

}
