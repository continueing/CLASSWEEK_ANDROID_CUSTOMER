package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.Review;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 5. 25..
 */
public class ViewForReviewDialog extends AbstractViewForActivity{
    private ProgressbarFooter progressbarFooter;
    private ListView lv_reviews;
    private ArrayAdapterForReviewListView arrayAdapterForReviewListView;
    private OnScrollOfListViewListener onScrollOfListViewListener;
    private TextView tv_titleAndNumber;

    public ViewForReviewDialog(Context context, OnScrollOfListViewListener anOnScrollOfListViewListener) {
        super(context);
        onScrollOfListViewListener = anOnScrollOfListViewListener;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_reviews,null);
    }

    @Override
    protected void initViews() {
        lv_reviews = (ListView)findViewById(R.id.lv_dialog_reviews);
        progressbarFooter = new ProgressbarFooter(lv_reviews, LayoutInflater.from(getContext()));
        progressbarFooter.setVisibility(true); // should be placed before set adapter
        arrayAdapterForReviewListView = new ArrayAdapterForReviewListView(getContext(), R.layout.item_review);
        lv_reviews.setAdapter(arrayAdapterForReviewListView);
        tv_titleAndNumber = (TextView) findViewById(R.id.tv_dialog_review_title_and_number);


    }

    @Override
    protected void setEvent() {
        lv_reviews.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean visibilityOfLastItem = false;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int aScrollState) {
                if(aScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    if(aScrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibilityOfLastItem)
                    {
                        onScrollOfListViewListener.atScrollIsOnEndItem();
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

    public void setData(int numberOfReview)
    {
        tv_titleAndNumber.setText("리뷰 ("+numberOfReview+")");
    }


    public void setProgressbarVisibility(boolean aVisibility)
    {
        this.progressbarFooter.setVisibility(aVisibility);
    }

    public void addReviewItemArrayList(ArrayList<ViewForReviewItem.IReviewItem> iReviewItems)
    {
        this.arrayAdapterForReviewListView.addAll(iReviewItems);
    }
}
