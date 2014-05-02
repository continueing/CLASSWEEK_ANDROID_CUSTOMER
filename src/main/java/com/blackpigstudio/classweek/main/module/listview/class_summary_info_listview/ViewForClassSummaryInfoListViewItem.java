package com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ViewForClassSummaryInfoListViewItem extends AbstractViewForListViewItem{
    private IClassSummaryInfoItem iClassSummaryInfoItem;

    private SmartImageView siv_class_introduction;
    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_company;
    private TextView tv_day_price;
    private TextView tv_month_discount_price;
    private TextView tv_month_original_price;
    private TextView tv_count_of_class_per_month;
    private TextView tv_discount_ratio;

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
        tv_title = (TextView) findViewById_(R.id.tv_class_summary_info_title);
        tv_time = (TextView) findViewById_(R.id.tv_class_summary_info_time);
        tv_company = (TextView) findViewById_(R.id.tv_class_summary_info_company);
        tv_day_price = (TextView) findViewById_(R.id.tv_class_summary_info_day_price);
        tv_month_discount_price = (TextView) findViewById_(R.id.tv_class_summary_info_month_discount_price);
        tv_month_original_price = (TextView) findViewById_(R.id.tv_class_summary_info_month_original_price);
        tv_month_original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_count_of_class_per_month = (TextView) findViewById_(R.id.tv_class_summary_info_count_of_class_per_month);
        tv_discount_ratio = (TextView) findViewById(R.id.tv_class_summary_info_discount_ratio);

    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        iClassSummaryInfoItem = (IClassSummaryInfoItem)aIListViewItem;
        siv_class_introduction.setImageUrl(iClassSummaryInfoItem.getFrontImageUrl());
        tv_title.setText(iClassSummaryInfoItem.getTitle());
        tv_time.setText(iClassSummaryInfoItem.getTime());
        tv_company.setText(iClassSummaryInfoItem.getCompany() + "(" + iClassSummaryInfoItem.getShortLocation()+")");
        tv_day_price.setText(iClassSummaryInfoItem.getOneDayPrice() +"원 / 1회" );
        tv_month_discount_price.setText(iClassSummaryInfoItem.getOneMonthDiscountPrice()+"원" );
        tv_month_original_price.setText("("+iClassSummaryInfoItem.getOneMonthOriginalPrice()+"원)");
        tv_count_of_class_per_month.setText( " / " +iClassSummaryInfoItem.getNumberOfClassPerMonth()+"회");
        tv_discount_ratio.setText(iClassSummaryInfoItem.getDiscountRatio()+"%");
    }

    public IClassSummaryInfoItem getIClassSummaryInfoItem()
    {
        return iClassSummaryInfoItem;
    }

}
