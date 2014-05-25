package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.Review;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ReviewDialog extends Dialog implements OnScrollOfListViewListener {
    public static final String SCREEN_NAME = "review";
    private ViewForReviewDialog view;
    private EasyTracker easyTracker;

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
        ArrayList<ViewForReviewItem.IReviewItem> iReviewItems = new ArrayList<ViewForReviewItem.IReviewItem>();
        iReviewItems.add(new Review());
        iReviewItems.add(new Review());
        iReviewItems.add(new Review());

        view.addReviewItemArrayList(iReviewItems);
    }

    @Override
    protected void onStart() {
        super.onStart();
        easyTracker = EasyTracker.getInstance(getOwnerActivity());
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME );
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
        easyTracker.activityStart(getOwnerActivity());
    }

    @Override
    protected void onStop() {
        super.onStop();
        easyTracker.activityStop(getOwnerActivity());
    }
}
