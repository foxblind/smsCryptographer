package com.bvblogic.examplearbvb.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.bvblogic.examplearbvb.db.domain.Chat;
import java.util.List;
import io.reactivex.Single;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat")
    Single<List<Chat>> getAll();

    @Query("SELECT COUNT(*) from chat")
    int countChats();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Chat... chats);

    @Delete
    void delete(Chat chat);
}
