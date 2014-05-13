package com.blackpigstudio.classweek.main.ui.menu.now_taking;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.MyClass;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.now_taking.listview.ViewForNowTakingClassListViewItem;
import com.blackpigstudio.classweek.main.ui.singn_in_up.SignInAndUpActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class NowTakingClassFragment extends Fragment implements HttpRequester.NetworkResponseListener {
    public static final String SCREEN_NAME = "nowTaking";
    ViewForNowTakingClassesFragment view;

    public NowTakingClassFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = new ViewForNowTakingClassesFragment(getActivity().getApplicationContext(),inflater,container);
        getActivity().getActionBar().setTitle(R.string.title_section2);
        requestMyClassListFromServer();
        return  view.getRoot();
    }

    private void requestMyClassListFromServer()
    {
        ClassRequest classRequest = new ClassRequest(getActivity());

        try {
            classRequest.getNowTaking(this);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getNowTaking fail :"+ e.toString());
        }

    }


    @Override
    public void onSuccess(JSONObject jsonObject) {

        JSONArray jsonArray = null;

        try {
            jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
        } catch (JSONException e) {
            AppTerminator.error(this, "JSONObject.getJSONArray(): " + e.toString());
        }

        ArrayList<ViewForNowTakingClassListViewItem.INowTakingClass> myClasses = new ArrayList<ViewForNowTakingClassListViewItem.INowTakingClass>();
        for(int i = 0 ; i < jsonArray.length() ; i++)
        {
            JSONObject jsonMyClassObject = null;
            try {
                jsonMyClassObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                AppTerminator.error(this, "jsonArray.getJSONObject: " + e.toString());
            }

            try {
                myClasses.add(new MyClass(jsonMyClassObject));
            } catch (JSONException e) {
                AppTerminator.error(this, "MyClass.new: " + e.toString());
            }
        }
        view.setData(myClasses);
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            Toast.makeText(getActivity(), getResources().getString(R.string.network_check_alert), Toast.LENGTH_LONG).show();
        }
        else if(errorCode ==  JsonResponseHandler.ERROR_CODE_HAVE_TO_LOGIN) {
            Intent intent = new Intent(getActivity(), SignInAndUpActivity.class);
            startActivity(intent);
        }
        else
        {
            AppTerminator.error(this, "classRequest.myclasss fail : " + errorCode);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(getActivity());
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(getActivity()).activityStop(getActivity());
    }
}
