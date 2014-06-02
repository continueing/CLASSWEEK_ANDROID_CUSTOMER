package com.blackpigstudio.classweek.main.ui.etc.exit_survey_dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;
import com.blackpigstudio.classweek.main.module.widget.button.SubmitButton;

/**
 * Created by continueing on 2014. 6. 2..
 */
public class ViewForExitSurveyDialog extends AbstractViewForActivity {
    private int currentChoice = 2;
    private Controller controller;
    private RadioGroup rg_choices;
    private SubmitButton sbt_survey_choice;
    private ProgressBar pb_survey_choice;

    public ViewForExitSurveyDialog(Context context, Controller aController) {
        super(context);
        controller = aController;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_exit_survey,null);
    }

    @Override
    protected void initViews() {
        rg_choices = (RadioGroup)findViewById(R.id.rg_exit_survey_list);
        sbt_survey_choice = (SubmitButton)findViewById(R.id.sbt_exit_survey_choice);
        pb_survey_choice = (ProgressBar)findViewById(R.id.pb_exit_survey_choice);
        sbt_survey_choice.init(pb_survey_choice);
        sbt_survey_choice.setEnabled(false);
    }

    @Override
    protected void setEvent() {
        rg_choices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId)
               {
                   case R.id.rb_exit_survey_first:
                       currentChoice = 0;
                       break;
                   case R.id.rb_exit_survey_second:
                       currentChoice = 1;
                       break;
                   case R.id.rb_exit_survey_third:
                       currentChoice = 2;
                       break;
               }
               sbt_survey_choice.setEnabled(true);
           }
        });

        sbt_survey_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onSurveyRequested(currentChoice);
            }
        });
    }

    public static interface Controller
    {
        public void onSurveyRequested(int aChoice);
    }
}
