package com.bvblogic.examplearbvb.fragment;


import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;


import com.bvblogic.examplearbvb.R;

import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.config.Config;

import com.bvblogic.examplearbvb.db.presenter.ChatPresenter;
import com.bvblogic.examplearbvb.fragment.core.BaseFragment;
import com.bvblogic.examplearbvb.mvp.core.FragmentById;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_chat_list)
public class ChatsFragment extends BaseFragment {

    android.support.v7.widget.RecyclerView chatList;



    @Bean
    ChatPresenter chatPresenter;
    @AfterViews
    public void init() {
        for(String permission : Config.PERMISSIONS)
        {
            if (ContextCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission},
                        3);
            }
        }
        chatPresenter.getAllChats();
        chatList = getActivity().findViewById(R.id.chatList);
    }

    @Click
    void btnTest()
    {
        ((BaseActivity)getActivity()).changeFragmentTo(new FragmentData(FragmentById.CREATE_CHAT_FRAGMENT));
    }


}
