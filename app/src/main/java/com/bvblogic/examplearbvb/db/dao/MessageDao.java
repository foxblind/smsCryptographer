package com.bvblogic.examplearbvb.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bvblogic.examplearbvb.db.domain.Message;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messages")
    Single<List<Message>> getAll();

    @Query("SELECT * FROM messages WHERE sender IS :sender")
    Single<Message> get(int sender);

    @Query("SELECT COUNT(*) from messages")
    int countMessages();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Message... messages);

    @Delete
    void delete(Message message);
}
