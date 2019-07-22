package com.bvblogic.examplearbvb.sender;

import android.telephony.SmsManager;

import com.bvblogic.examplearbvb.sender.core.Sender;

public class SmsSender implements Sender {
    @Override
    public void send(String recipient, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(recipient, null, message, null, null);
    }
}
