package com.blackpigstudio.classweek.main.module.classsummaryinfolistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.loopj.android.image.SmartImageView;

/**
 * Created by continueing on 2014. 3. 31..
 */
public class ClassSummaryInfoLayout extends LinearLayout{
    private IClassSummaryInfo iClassSummaryInfo;

    private SmartImageView siv_class_introduction;
    private TextView tv_detail_location;
    private TextView tv_hook_sentence;
    private TextView tv_title;
    private TextView tv_day_price;
    private TextView tv_month_price;

    public ClassSummaryInfoLayout(Context context) {
        this(context, null);
    }

    public ClassSummaryInfoLayout(Context context, AttributeSet attrs) {
        super(context);
        initViews();
    }

    private void initViews()
    {
        View root = inflate(getContext(), R.layout.item_class_summary_information, this);
        siv_class_introduction = (SmartImageView)root.findViewById(R.id.siv_class_introduction);
        tv_detail_location = (TextView)findViewById(R.id.tv_class_summary_info_detail_location);
        tv_hook_sentence = (TextView)root.findViewById(R.id.tv_class_summary_info_hook_sentence);
        tv_title = (TextView)root.findViewById(R.id.tv_class_summary_info_title);
        tv_day_price = (TextView)root.findViewById(R.id.tv_class_summary_info_day_price);
        tv_month_price = (TextView)root.findViewById(R.id.tv_class_summary_info_month_price);
    }

    public void setData(IClassSummaryInfo aIClassSummaryInfo)
    {
        iClassSummaryInfo = aIClassSummaryInfo;
        siv_class_introduction.setImageUrl(aIClassSummaryInfo.getURLOfImage());
        tv_detail_location.setText(aIClassSummaryInfo.getDetailLocation());
        tv_title.setText(aIClassSummaryInfo.getTitle());
        tv_hook_sentence.setText(aIClassSummaryInfo.getHookSentence());
        tv_day_price.setText(aIClassSummaryInfo.getDayPrice());
        tv_month_price.setText(aIClassSummaryInfo.getMonthPrice());
    }


    public interface IClassSummaryInfo
    {
        public String getTitle();
        public String getHookSentence();
        public String getDetailLocation();
        public String getDayPrice();
        public String getMonthPrice();
        public String getURLOfImage();
    }
}
