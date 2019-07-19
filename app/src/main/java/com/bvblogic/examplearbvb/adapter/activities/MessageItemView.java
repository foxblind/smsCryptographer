package com.bvblogic.examplearbvb.adapter.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.domain.Message;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_message)
public class MessageItemView extends LinearLayout {
    @ViewById(R.id.messageText)
    TextView messageText;

    public MessageItemView(BaseActivity context) {
        super(context);
    }

    @SuppressLint("CheckResult")
    public void bind(Message message, int i) {
        messageText.setText(String.valueOf(message.getText()));
    }
}
