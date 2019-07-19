package com.bvblogic.examplearbvb.bean.chat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bvblogic.examplearbvb.adapter.activities.ChatAdapter;

import com.bvblogic.examplearbvb.bean.core.Bean;
import com.bvblogic.examplearbvb.db.domain.Chat;


import org.androidannotations.annotations.EBean;

import java.util.List;
@EBean
public class ProviderBeanChat extends Bean {

    @org.androidannotations.annotations.Bean
    public ChatAdapter adapter;

    public void initAdapter(RecyclerView rv) {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        rv.setAdapter(adapter);
    }

    public void initAdapter(List<Chat> chats) {
        adapter.setItems(chats);

    }
}
