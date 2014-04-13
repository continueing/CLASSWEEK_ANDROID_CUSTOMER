package com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.IListViewItem;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ViewForClassSummaryInfoListViewItem extends AbstractViewForListViewItem{
    private IClassSummaryInfoItem iClassSummaryInfoItem;

    private SmartImageView siv_class_introduction;
    private TextView tv_detail_location;
    private TextView tv_hook_sentence;
    private TextView tv_title;
    private TextView tv_day_price;
    private TextView tv_month_price;

    public ViewForClassSummaryInfoListViewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_class_summary_information, this);
    }

    @Override
    protected void initViews() {
        siv_class_introduction = (SmartImageView) findViewById_(R.id.siv_class_summary_info_front);
        tv_detail_location = (TextView) findViewById_(R.id.tv_class_summary_info_detail_location);
        tv_hook_sentence = (TextView) findViewById_(R.id.tv_class_summary_info_hook_sentence);
        tv_title = (TextView) findViewById_(R.id.tv_class_summary_info_title);
        tv_day_price = (TextView) findViewById_(R.id.tv_class_summary_info_day_price);
        tv_month_price = (TextView) findViewById_(R.id.tv_class_summary_info_month_price);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        iClassSummaryInfoItem = (IClassSummaryInfoItem)aIListViewItem;
        siv_class_introduction.setImageUrl(iClassSummaryInfoItem.getURLOfImage());
        tv_detail_location.setText(iClassSummaryInfoItem.getDetailLocation());
        tv_title.setText(iClassSummaryInfoItem.getTitle());
        tv_hook_sentence.setText(iClassSummaryInfoItem.getHookSentence());
        tv_day_price.setText(iClassSummaryInfoItem.getDayPrice());
        tv_month_price.setText(iClassSummaryInfoItem.getMonthPrice());
    }

    public IClassSummaryInfoItem getIClassSummaryInfoItem()
    {
        return iClassSummaryInfoItem;
    }

}
