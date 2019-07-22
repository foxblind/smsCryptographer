package com.bvblogic.examplearbvb.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bvblogic.examplearbvb.R;

import com.bvblogic.examplearbvb.db.domain.Message;
import com.bvblogic.examplearbvb.db.presenter.MessagePresenter;
import com.bvblogic.examplearbvb.fragment.core.BaseFragment;
import com.bvblogic.examplearbvb.sender.core.Sender;
import com.bvblogic.examplearbvb.sender.SmsSender;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_message_list)
public class MessagesFragment extends BaseFragment {

    android.support.v7.widget.RecyclerView messageList;
    TextView header;
    @Bean
    MessagePresenter messagePresenter;

    @ViewById(R.id.btnSend)
    Button btnSend;

    @ViewById(R.id.inpMessage)
    EditText inpMessage;
    @AfterViews
    public void init() {
        messagePresenter.getAllMessages();
        messageList = getActivity().findViewById(R.id.messageList);
        header = getActivity().findViewById(R.id.header);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String chatHeader = sharedPref.getString("HEADER", "");

        header.setText(chatHeader);
        Toast.makeText(getContext(), header.getText().toString(),Toast.LENGTH_LONG).show();
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        messagePresenter.getMessagesFromChat(chatHeader);
    }

    @Click
    void btnSend(){
        String messageText = inpMessage.getText().toString();
        String recipient = header.getText().toString();
        SharedPreferences pref = getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        int id = pref.getInt("MSG_ID", 0);

        Message message = new Message();
        message.setId(id++);
        message.setChat(recipient);
        message.setSender("me");
        message.setText(messageText);

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("MSG_ID", id);
        editor.apply();
        Sender sender = new SmsSender();
        sender.send(recipient, messageText);

        inpMessage.setText("");
        messagePresenter.saveMessage(message);
    }
}
