package com.bvblogic.examplearbvb.db.domain;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;


@Entity(tableName = "chat",indices = {@Index(value = {"phone"},
        unique = true)})
public class Chat {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "phone")
    private String phone = "";


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
