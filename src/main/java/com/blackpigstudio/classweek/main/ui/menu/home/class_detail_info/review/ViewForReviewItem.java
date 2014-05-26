package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ViewForReviewItem extends AbstractViewForListViewItem {
    TextView tv_contents;
    ImageView iv_rank_stars[];
    TextView tv_written_date;


    public ViewForReviewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_review, this);
    }

    @Override
    protected void initViews() {
        tv_contents = (TextView)findViewById_(R.id.tv_item_review_contents);
        iv_rank_stars = new ImageView[5];
        iv_rank_stars[0] = (ImageView)findViewById_(R.id.iv_rank_star_1);
        iv_rank_stars[1] = (ImageView)findViewById_(R.id.iv_rank_star_2);
        iv_rank_stars[2] = (ImageView)findViewById_(R.id.iv_rank_star_3);
        iv_rank_stars[3] = (ImageView)findViewById_(R.id.iv_rank_star_4);
        iv_rank_stars[4] = (ImageView)findViewById_(R.id.iv_rank_star_5);
        tv_written_date = (TextView)findViewById_(R.id.tv_item_review_written_date);
    }

    private void removeRankStarts()
    {
        for(ImageView rankStart : iv_rank_stars)
        {
            rankStart.setVisibility(INVISIBLE);
        }
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        IReviewItem reviewItem= (IReviewItem)aIListViewItem;
        tv_contents.setText(reviewItem.getContents());

        if(reviewItem.getRank() == 0)
        {
            removeRankStarts();
        }
        else
        {
            for(int i = 0 ; i < reviewItem.getRank() ; i++ )
            {
                iv_rank_stars[i].setImageResource(R.drawable.ic_rank_normal);
            }
        }
        tv_written_date.setText(reviewItem.getWrittenDate());
    }

    public void setReview(IListViewItem aIListViewItem)
    {
        setData(aIListViewItem);
    }


    public static interface IReviewItem extends IListViewItem
    {
        public int getRank();
        public String getContents();
        public String getWrittenDate();
    }
}
