package com.blackpigstudio.classweek.main.ui.menu.contact;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.network.UserRequest;
import com.blackpigstudio.classweek.main.module.preference.UserPreference;
import com.blackpigstudio.classweek.main.ui.singn_in_up.SignInAndUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactFragment extends Fragment implements HttpRequester.NetworkResponseListener {
    private MenuItem mi_inquiry;
    private EditText et_contact;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_contact, container, false);
        getActivity().getActionBar().setTitle("문의하기");
        et_contact = (EditText) root.findViewById(R.id.et_contact);
        return root;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.inquiry, menu);
        mi_inquiry = menu.findItem(R.id.action_inquiry);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_inquiry) {
            UserPreference userPreference = new UserPreference(getActivity());
            if(userPreference.isLoggedIn())
            {
                requestContact();
            }
            else
            {
                Intent intent = new Intent(getActivity(), SignInAndUpActivity.class);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestContact()
    {
        prepareUIForRequest();
        UserRequest userRequest = new UserRequest(getActivity());
        try {
            userRequest.contact(et_contact.getText().toString(),this);
        } catch (JSONException e) {
            AppTerminator.error(this, "UserRequest.contact : " + e.toString());
        }
    }

    private void prepareUIForRequest()
    {
        mi_inquiry.setVisible(false);
        ((ActionBarActivity)getActivity()).setSupportProgressBarIndeterminateVisibility(true);
        et_contact.setEnabled(false);
    }

    private void releaseUIAfterRequest()
    {
        mi_inquiry.setVisible(true);
        ((ActionBarActivity)getActivity()).setSupportProgressBarIndeterminateVisibility(false);
        et_contact.setEnabled(true);
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        releaseUIAfterRequest();
        et_contact.setText("");
        Toast.makeText(getActivity(), "정상적으로 문의가 접수되었습니다.\n최대한 빠르게 연락드리겠습니다.\n감사합니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        releaseUIAfterRequest();
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            Toast.makeText(getActivity(), getResources().getString(R.string.network_check_alert), Toast.LENGTH_LONG).show();
        }
        else
        {
            AppTerminator.error(this, "UserRequest.contact fail : " + errorCode);
        }
    }

}
