package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.Review;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ReviewDialog extends Dialog {

    public ReviewDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reviews);

        ListView listView = (ListView)findViewById(R.id.lv_dialog_reviews);
        ArrayAdapterForReviewListView arrayAdapterForReviewListView = new ArrayAdapterForReviewListView(getContext(),R.layout.item_review);
        listView.setAdapter(arrayAdapterForReviewListView);
        arrayAdapterForReviewListView.add(new Review());
        arrayAdapterForReviewListView.add(new Review());
        arrayAdapterForReviewListView.add(new Review());
    }
}
