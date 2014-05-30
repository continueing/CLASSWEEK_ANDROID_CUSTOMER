package com.blackpigstudio.classweek.main.ui.admin.login_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 5. 29..
 */
public class ViewForLoginActivity extends AbstractViewForActivity {
    EditText et_email;
    EditText et_password;

    public ViewForLoginActivity(Context context)
    {
        super(context);
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_login, null);
    }

    @Override
    protected void initViews() {
        this.et_email = (EditText) findViewById(R.id.et_login_email);
        this.et_email.requestFocus();
        this.et_password= (EditText) findViewById(R.id.et_login_password);
    }

    @Override
    protected void setEvent() {

    }

    public String getEmail(){ return this.et_email.getText().toString();}

    public String getPassword()
    {
        return this.et_password.getText().toString();
    }


    public void lockAllEditTexts()
    {
        this.et_email.setEnabled(false);
        this.et_password.setEnabled(false);
    }

    public void releaseAllEditTexts()
    {
        this.et_email.setEnabled(true);
        this.et_password.setEnabled(true);
    }

}
