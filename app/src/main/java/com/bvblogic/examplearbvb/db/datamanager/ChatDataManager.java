package com.bvblogic.examplearbvb.db.datamanager;

import com.bvblogic.examplearbvb.db.core.AppDatabase;
import com.bvblogic.examplearbvb.db.datamanager.core.DBView;
import com.bvblogic.examplearbvb.db.domain.Chat;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ChatDataManager {

    public void getAllChats(AppDatabase appDatabase, DBView<List<Chat>> listDBView) {
        listDBView.showWait();
        appDatabase.chatDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Chat>>() {
                    @Override
                    public void onSuccess(List<Chat> chats) {
                        listDBView.onSuccess(chats);
                        listDBView.hideWait();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listDBView.onError(e);
                        listDBView.hideWait();
                    }
                });
    }

    public void saveChat(Chat chat, AppDatabase appDatabase, DBView<Long> listDBView) {
        listDBView.showWait();
        appDatabase.chatDao().insertAll(chat);
    }
}
