package com.bvblogic.examplearbvb.adapter.activities;

import android.annotation.SuppressLint;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.db.domain.Chat;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.item_chat)
public class ChatItemView extends RelativeLayout {

    @ViewById(R.id.phone)
    TextView phone;


    public ChatItemView(BaseActivity context) {
        super(context);
    }

    @SuppressLint("C")
    public void bind(Chat chat, int i) {
        phone.setText(String.valueOf(chat.getPhone()));
    }


}
