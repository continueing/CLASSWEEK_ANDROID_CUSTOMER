package com.blackpigstudio.classweek.main.menus.home.dance;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blackpigstudio.classweek.R;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 3. 29..
 */
public class SubcategoryFragmentLayout extends LinearLayout{
    public SubcategoryFragmentLayout(Context context, ViewGroup container) {
        super(context);
        initViews(container);

    }

    public SubcategoryFragmentLayout(Context context, AttributeSet attrs, ViewGroup container) {
        super(context, attrs);
        initViews(container);
    }

    private void initViews(ViewGroup container)
    {
        View root = inflate(getContext(), R.layout.fragment_sub_category,container);
        SmartImageView siv_urban_hiphop = (SmartImageView) root.findViewById(R.id.siv_dance_subcategory_urban_hiphop);
        SmartImageView siv_kpop = (SmartImageView) root.findViewById(R.id.siv_dance_subcategory_kpop);
        SmartImageView siv_salsa = (SmartImageView) root.findViewById(R.id.siv_dance_subcategory_salsa);
        siv_urban_hiphop.setImageUrl("http://farm4.staticflickr.com/3091/2892103441_7d87897f78_o.jpg");
        siv_kpop.setImageUrl("http://farm7.staticflickr.com/6151/6171681106_fe20ce77ff_o.jpg");
        siv_salsa.setImageUrl("http://farm4.staticflickr.com/3284/3035753275_438192b670_o.jpg");
    }
}
