package com.blackpigstudio.classweek.main.ui.menus.home.music;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AbstractViewForFragment;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 4. 1..
 */
public class ViewForMusicSubcategoryFragment extends AbstractViewForFragment {
    private OnSubCategoryChooseListener onSubCategoryChooseListener;
    SmartImageView siv_urban_hiphop;

    public ViewForMusicSubcategoryFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, OnSubCategoryChooseListener onSubCategoryChooseListener) {
        super(context, layoutInflater, container);
        this.onSubCategoryChooseListener = onSubCategoryChooseListener;

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_music_subcategory,container, false);
    }

    @Override
    protected void initViews() {
        siv_urban_hiphop = (SmartImageView) findViewById(R.id.siv_music_subcategory_urban_hiphop);
        SmartImageView siv_kpop = (SmartImageView) findViewById(R.id.siv_music_subcategory_kpop);
        SmartImageView siv_salsa = (SmartImageView) findViewById(R.id.siv_music_subcategory_salsa);
        siv_urban_hiphop.setImageUrl("http://farm4.staticflickr.com/3091/2892103441_7d87897f78_o.jpg");
        siv_kpop.setImageUrl("http://farm7.staticflickr.com/6151/6171681106_fe20ce77ff_o.jpg");
        siv_salsa.setImageUrl("http://farm4.staticflickr.com/3284/3035753275_438192b670_o.jpg");

    }

    @Override
    protected void setEvents() {
        siv_urban_hiphop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubCategoryChooseListener.onSubCategoryChoose(0);
            }
        });
    }


    public interface OnSubCategoryChooseListener
    {
        public void onSubCategoryChoose(int index);
    }
}
