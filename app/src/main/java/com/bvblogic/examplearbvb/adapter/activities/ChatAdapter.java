package com.bvblogic.examplearbvb.adapter.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.adapter.core.RecyclerViewAdapterBase;
import com.bvblogic.examplearbvb.adapter.core.ViewWrapper;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.mvp.core.FragmentById;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;


import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
@EBean
public class ChatAdapter extends RecyclerViewAdapterBase<Chat, ChatItemView> {

    @RootContext
    protected BaseActivity activity;
    @Override
    protected ChatItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ChatItemView_.build(activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<ChatItemView> activitiesItemViewViewWrapper, int i) {
        ChatItemView view = activitiesItemViewViewWrapper.getView();
        Chat chat = items.get(i);
        view.setTag(i);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("HEADER", view.phone.getText().toString());
                editor.apply();
                activity.changeFragmentTo(new FragmentData(FragmentById.MESSAGES_FRAGMENT));

            }
        });

        view.bind(chat, i);
    }



}
