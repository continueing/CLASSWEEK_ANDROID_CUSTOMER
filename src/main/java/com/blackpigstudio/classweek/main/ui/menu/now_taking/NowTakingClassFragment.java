package com.blackpigstudio.classweek.main.ui.menu.now_taking;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;

import org.json.JSONObject;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class NowTakingClassFragment extends Fragment implements HttpRequester.NetworkResponseListener {
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

    }


    @Override
    public void onSuccess(JSONObject jsonObject) {

//        view.setData();
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            Toast.makeText(getActivity(), getResources().getString(R.string.network_check_alert), Toast.LENGTH_LONG).show();
        }
        else
        {
            AppTerminator.error(this, "classRequest.myclasss fail : " + errorCode);
        }
    }
}
