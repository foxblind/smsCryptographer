package com.bvblogic.examplearbvb.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bvblogic.examplearbvb.R;

import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.presenter.ChatPresenter;
import com.bvblogic.examplearbvb.fragment.core.BaseFragment;
import com.bvblogic.examplearbvb.mvp.core.ToolBarById;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;

import java.util.Random;

import carbon.widget.FloatingActionButton;

@EFragment(R.layout.fragment_chat_list)
public class ChatsFragment extends BaseFragment {

    android.support.design.widget.FloatingActionButton btnTest;
    android.support.v7.widget.RecyclerView chatList;

    @Bean
    ChatPresenter chatPresenter;
    @AfterViews
    public void init() {
        chatPresenter.getAllChats();
        final SharedPreferences[] sharedPref = {getActivity().getPreferences(Context.MODE_PRIVATE)};
        final int[] i = {sharedPref[0].getInt("ID", 0)};
        BaseFragment.changeColorBar(getActivity(), BaseFragment.ColorBar.BLUE);
        //initToolBar(ToolBarById.CLOSE);
        btnTest = getActivity().findViewById(R.id.btnTest);
        chatList = getActivity().findViewById(R.id.chatList);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat chat = new Chat();
                chat.setId(i[0]);
                chat.setPhone(""+ i[0]++);
                chatPresenter.saveChat(chat);
                chatPresenter.getAllChats();
                SharedPreferences.Editor editor = sharedPref[0].edit();
                editor.putInt("ID", i[0]);
                editor.apply();
            }
        });



    }
}
