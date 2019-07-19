package com.bvblogic.examplearbvb.db.presenter;

import android.support.v7.widget.RecyclerView;

import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.bean.chat.ProviderBeanChat;
import com.bvblogic.examplearbvb.bean.message.ProviderBeanMessage;
import com.bvblogic.examplearbvb.db.datamanager.ChatDataManager;
import com.bvblogic.examplearbvb.db.datamanager.MessageDataManager;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.domain.Message;
import com.bvblogic.examplearbvb.db.presenter.core.Presenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import java.util.List;
@EBean
public class MessagePresenter extends Presenter<List<Message>> {

    @ViewById(R.id.messageList)
    RecyclerView messageList;

    @Bean
    ProviderBeanMessage providerBeanMessage;
    @Override
    public void onSuccess(List<Message> messages) {
        providerBeanMessage.initAdapter(messageList);
        providerBeanMessage.initAdapter(messages);

    }

    public void getAllMessages() {
        new MessageDataManager().getAllMessages(appDatabase, this);
    }
}
