package com.bvblogic.examplearbvb.db.datamanager;

import com.bvblogic.examplearbvb.db.core.AppDatabase;
import com.bvblogic.examplearbvb.db.datamanager.core.DBView;

import com.bvblogic.examplearbvb.db.domain.Message;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MessageDataManager {
    public void getAllMessages(AppDatabase appDatabase, DBView<List<Message>> listDBView) {
        listDBView.showWait();
        appDatabase.messageDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> messages) {
                        listDBView.onSuccess(messages);
                        listDBView.hideWait();

                    }

                    @Override
                    public void onError(Throwable e) {
                        listDBView.onError(e);
                        listDBView.hideWait();
                    }
                });
    }

    public void saveMessage(Message message, AppDatabase appDatabase, DBView<Long> listDBView) {
        listDBView.showWait();
        appDatabase.messageDao().insertAll(message);

    }

    public void get(AppDatabase appDatabase, DBView<List<Message>> listDBView, String from)
    {
        listDBView.showWait();
        appDatabase.messageDao().get(from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> messages) {
                        listDBView.onSuccess(messages);
                        listDBView.hideWait();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listDBView.onError(e);
                        listDBView.hideWait();
                    }
                });
    }
}
