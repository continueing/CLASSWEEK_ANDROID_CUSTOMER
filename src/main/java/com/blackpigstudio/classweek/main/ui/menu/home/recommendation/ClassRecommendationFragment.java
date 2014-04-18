package com.blackpigstudio.classweek.main.ui.menu.home.recommendation;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummeryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;

import org.json.JSONException;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends AbstractHomeFragment implements OnClassSummeryInfoChooseListener{
    public static final int SPINNER_ITEM_INDEX = 0;
    private static ClassRecommendationFragment instance;
    private ViewForClassRecommendationFragment classRecommendationFragmentView;

    public ClassRecommendationFragment() {
        setSpinnerItemIndex(SPINNER_ITEM_INDEX);
    }

    public static ClassRecommendationFragment getInstance()
    {
        if(instance == null)
            instance = new ClassRecommendationFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        classRecommendationFragmentView = new ViewForClassRecommendationFragment( getActivity(), inflater,container, this);
        requestRecommendedClassSummaryInfoFromServer();
        return classRecommendationFragmentView.getRoot();
    }

    private void requestRecommendedClassSummaryInfoFromServer() {
//        ClassRequest classRequest = new ClassRequest();
//        try {
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onClassSummeryInfoChoose(IClassSummaryInfoItem iClassSummaryInfoItem) {
        Intent intent = new Intent(getActivity(),ClassDetailInfoActivity.class);
        startActivity(intent);
    }
}
