package com.blackpigstudio.classweek.main.ui.menus.home.subcategory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class ViewForSubcategoryListViewItem extends LinearLayout {
    private View root;
    private ISubcategory iSubcategory;

    private TextView tv_subcategory_title;
    private SmartImageView siv_subcategory_front;


    public ViewForSubcategoryListViewItem(Context context) {
        this(context,null);
    }

    public ViewForSubcategoryListViewItem(Context context, AttributeSet attrs) {
        super(context);
        root = inflate(getContext(), R.layout.item_subcategory, this);
        initViews();
    }

    private void initViews() {
        tv_subcategory_title = (TextView)root.findViewById(R.id.tv_subcategory_title);
        siv_subcategory_front = (SmartImageView)root.findViewById(R.id.siv_subcategory_front);
    }


    public void setData(ISubcategory aISubcategory)
    {
        this.iSubcategory = aISubcategory;
        tv_subcategory_title.setText(iSubcategory.getTitle());
        siv_subcategory_front.setImageUrl(iSubcategory.getFrontImageUrl());
    }

    public static interface ISubcategory
    {
        public String getTitle();
        public String getFrontImageUrl();
    }
}
