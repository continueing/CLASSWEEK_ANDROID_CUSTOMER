package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.inquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class ViewForInquiryActivity  extends AbstractViewForActivity{
    private EditText et_inquiry;

    public ViewForInquiryActivity(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_inquiry,null);
    }

    @Override
    protected void initViews() {
        et_inquiry = (EditText)findViewById(R.id.et_inquiry);
//        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(et_inquiry, InputMethodManager.SHOW_FORCED);
    }

    @Override
    protected void setEvent() {

    }


    public void lockAllEditTexts()
    {
        et_inquiry.setEnabled(false);
    }

    public void releaseAllEditTexts()
    {
        et_inquiry.setEnabled(false);
    }

    public String getInquiryText()
    {
        return et_inquiry.getText().toString();
    }
}
