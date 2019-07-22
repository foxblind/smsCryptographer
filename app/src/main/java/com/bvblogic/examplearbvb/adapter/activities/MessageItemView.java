package com.bvblogic.examplearbvb.adapter.activities;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.db.domain.Message;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_message)
public class MessageItemView extends RelativeLayout {
    @ViewById(R.id.messageText)
    TextView messageText;

    public MessageItemView(BaseActivity context) {
        super(context);
    }

    @SuppressLint("CheckResult")
    public void bind(Message message, int i) {
        if(message.getSender().equals("me"))
            messageText.setGravity(Gravity.END);
        messageText.setText(String.valueOf(message.getText()));

    }

    public String getText()
    {
        return messageText.getText().toString();
    }
}
