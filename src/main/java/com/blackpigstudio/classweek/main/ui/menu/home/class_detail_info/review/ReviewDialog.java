package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.Review;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ReviewDialog extends Dialog {
    private ProgressbarFooter progressbarFooter;
    private ListView lv_reviews;
    private ArrayAdapterForReviewListView arrayAdapterForReviewListView;

    public ReviewDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reviews);

        lv_reviews = (ListView)findViewById(R.id.lv_dialog_reviews);
        progressbarFooter = new ProgressbarFooter(lv_reviews, getLayoutInflater());
        progressbarFooter.setVisibility(true);
        arrayAdapterForReviewListView = new ArrayAdapterForReviewListView(getContext(), R.layout.item_review);
        lv_reviews.setAdapter(arrayAdapterForReviewListView);

        arrayAdapterForReviewListView.add(new Review());
        arrayAdapterForReviewListView.add(new Review());
        arrayAdapterForReviewListView.add(new Review());

        setEvent();
    }

    public void setEvent()
    {
        lv_reviews.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean visibilityOfLastItem = false;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int aScrollState) {
                if(aScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    if(aScrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibilityOfLastItem)
                    {
                        // end of item call request to server

                        arrayAdapterForReviewListView.add(new Review());
                        arrayAdapterForReviewListView.add(new Review());
                        arrayAdapterForReviewListView.add(new Review());
                        arrayAdapterForReviewListView.add(new Review());
                        arrayAdapterForReviewListView.add(new Review());
                        arrayAdapterForReviewListView.add(new Review());
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int aFirstVisibleItem, int aVisibleItemCount, int aTotalItemCount)
            {
                visibilityOfLastItem = aFirstVisibleItem + aVisibleItemCount >= aTotalItemCount;
            }
        });
    }


}
