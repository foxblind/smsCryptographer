package com.bvblogic.examplearbvb.db.presenter;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.bean.chat.ProviderBeanChat;
import com.bvblogic.examplearbvb.db.datamanager.ChatDataManager;
import com.bvblogic.examplearbvb.db.domain.Chat;

import com.bvblogic.examplearbvb.db.presenter.core.Presenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import java.util.List;
@EBean
public class ChatPresenter extends Presenter<List<Chat>> {

    @ViewById(R.id.chatList)
    RecyclerView chatList;

    @Bean
    ProviderBeanChat providerBeanChat;
    @Override
    public void onSuccess(List<Chat> chats) {
        providerBeanChat.initAdapter(chatList);
        providerBeanChat.initAdapter(chats);
    }

    public void getAllChats() {
        new ChatDataManager().getAllChats(appDatabase, this);
    }

    public void saveChat(Chat chat) {
        new ChatDataManager().saveChat(chat, appDatabase, new Presenter<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                Toast.makeText(activity, "New Item", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
