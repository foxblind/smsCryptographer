package com.bvblogic.examplearbvb.db.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "messages", foreignKeys = @ForeignKey(
        entity = Chat.class,
        parentColumns = "phone",
        childColumns = "from"))
public class Message {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "from")
    private String chat;

    @ColumnInfo(name = "sender")
    private String sender;

    @ColumnInfo(name = "text")
    private String text;

    public Message(int id, String chat, String sender, String text)
    {
        this.id = id;
        this.chat = chat;
        this.sender = sender;
        this.text =text;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chatId) {
        this.chat = chatId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
