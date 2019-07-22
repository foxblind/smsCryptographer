package com.bvblogic.examplearbvb.adapter.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bvblogic.examplearbvb.activity.core.BaseActivity;
import com.bvblogic.examplearbvb.adapter.core.RecyclerViewAdapterBase;
import com.bvblogic.examplearbvb.adapter.core.ViewWrapper;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.domain.Message;
import com.bvblogic.examplearbvb.mvp.core.FragmentById;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class MessageAdapter extends RecyclerViewAdapterBase<Message, MessageItemView> {

    @RootContext
    protected BaseActivity activity;

    @Override
    protected MessageItemView onCreateItemView(ViewGroup parent, int viewType) {
        return MessageItemView_.build(activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<MessageItemView> activitiesItemViewViewWrapper, int i) {
        MessageItemView view = activitiesItemViewViewWrapper.getView();
        Message message = items.get(i);
        view.setTag(i);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"HERE", Toast.LENGTH_SHORT).show();

            }
        });
        view.bind(message, i);

    }
}
