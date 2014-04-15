package com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.class_detail_info.booking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blackpigstudio.classweek.R;

/**
 * Created by continueing on 2014. 4. 14..
 */
public class ClassTypeSelectionDialog extends Dialog {
    private Button bt_class_type_select;


    public ClassTypeSelectionDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class_type_selection);
        bt_class_type_select = (Button)findViewById(R.id.bt_dialog_class_type_selection_select);
        setEvents();
    }

    private void setEvents()
    {
        bt_class_type_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassTypeSelectionDialog.this.dismiss();
            }
        });
    }
}
