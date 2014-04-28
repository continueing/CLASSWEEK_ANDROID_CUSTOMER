package com.blackpigstudio.classweek.main.module.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 4. 28..
 */
public class CheckButton extends Button{

    private boolean isChecked = false;

    public CheckButton(Context context) {
        this(context, null);
    }

    public CheckButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        adjustBackground();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            isChecked = !isChecked;
            adjustBackground();
        }
        return super.onTouchEvent(event);
    }

    public void adjustBackground()
    {
        if(isChecked())
        {
            setBackgroundResource(R.drawable.bg_info_sel);
        }
        else
        {
            setBackgroundResource(R.drawable.bg_info_nor);
        }
    }



    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean anFlag)
    {
        this.isChecked = anFlag;
    }

}
