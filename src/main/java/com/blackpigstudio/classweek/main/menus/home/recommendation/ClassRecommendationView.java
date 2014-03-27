package com.blackpigstudio.classweek.main.menus.home.recommendation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 3. 26..
 */

public class ClassRecommendationView extends LinearLayout {

    public ClassRecommendationView(Context context, ViewGroup container) {
        super(context);
        initViews(container);
    }

    public ClassRecommendationView(Context context, AttributeSet attrs, ViewGroup container) {
        super(context, attrs);
        initViews(container);
    }

    public void initViews(ViewGroup container)
    {
        View root = inflate(getContext(), R.layout.fragment_class_recommendation, container);
    }
}
