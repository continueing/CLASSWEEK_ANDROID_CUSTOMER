package com.blackpigstudio.classweek.main.ui.singn_in_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

/**
 * Created by continueing on 2014. 4. 8..
 */
public class ViewForSignInAndUpActivity extends AbstractViewForActivity {
    EditText et_email;
    EditText et_password;
    EditText et_passwordConfirmation;

    public ViewForSignInAndUpActivity(Context context)
    {
        super(context);
        initViews();
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_sign_in_and_up, null);
    }

    @Override
    protected void initViews() {
        this.et_email = (EditText) findViewById(R.id.et_email);
        this.et_password= (EditText) findViewById(R.id.et_password);
        this.et_passwordConfirmation = (EditText) findViewById(R.id.et_password_confirmation);
    }

    @Override
    protected void setEvent() {

    }

    public String getEmail(){ return this.et_email.getText().toString();}

    public String getPassword()
    {
        return this.et_password.getText().toString();
    }

    public String getPasswordConfirm()
    {
        return this.et_passwordConfirmation.getText().toString();
    }

    public void lockAllEditTexts()
    {
        this.et_email.setEnabled(false);
        this.et_password.setEnabled(false);
        this.et_passwordConfirmation.setEnabled(false);
    }

    public void releaseAllEditTexts()
    {
        this.et_email.setEnabled(true);
        this.et_password.setEnabled(true);
        this.et_passwordConfirmation.setEnabled(true);
    }
}
