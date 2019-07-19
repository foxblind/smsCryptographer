package com.bvblogic.examplearbvb.fragment;

import android.view.View;
import android.widget.Toast;

import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.db.presenter.ChatPresenter;
import com.bvblogic.examplearbvb.db.presenter.MessagePresenter;
import com.bvblogic.examplearbvb.fragment.core.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_message_list)
public class MessagesFragment extends BaseFragment {

    android.support.v7.widget.RecyclerView messageList;

    @Bean
    MessagePresenter messagePresenter;

    @AfterViews
    public void init() {
        messagePresenter.getAllMessages();
        messageList = getActivity().findViewById(R.id.messageList);

    }
}
