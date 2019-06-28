package com.exe.mehmood.fireapp;

import java.util.Date;

public class Message {

    private String messageText;
    private String messageSender;
    private long messageTime;

    public Message(String messageText, String messageSender) {
        this.messageText = messageText;
        this.messageSender = messageSender;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public Message() {

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
