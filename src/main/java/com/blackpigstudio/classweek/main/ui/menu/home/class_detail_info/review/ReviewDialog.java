package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.Review;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ReviewDialog extends Dialog implements OnScrollOfListViewListener {
    private ViewForReviewDialog view;

    public ReviewDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new ViewForReviewDialog(getContext(), this);
        setContentView(view.getRoot());

    }


    @Override
    public void atScrollIsOnEndItem() {

    }
}
