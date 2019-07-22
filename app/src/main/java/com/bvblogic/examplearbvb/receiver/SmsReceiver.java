package com.bvblogic.examplearbvb.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import com.bvblogic.examplearbvb.db.domain.Message;
import com.bvblogic.examplearbvb.db.presenter.MessagePresenter;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EReceiver;


@EReceiver
public class SmsReceiver extends BroadcastReceiver {


    static Message message;
    static String chat;
    @Bean
    MessagePresenter messagePresenter;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
        {
            String smsSender = "";
            String smsBody = "";
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent))
            {
                smsSender = smsMessage.getDisplayOriginatingAddress();
                smsBody += smsMessage.getMessageBody();
            }
            SharedPreferences pref = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            int id = pref.getInt("MSG_ID", 0);

            message = new Message(id++, smsSender, "not_me", smsBody);

            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("MSG_ID", id);
            editor.apply();

            new notifyAsyncTask().execute(messagePresenter);

        }
    }

    private static class notifyAsyncTask extends AsyncTask<MessagePresenter, Void, Void> {

        @Override
        protected Void doInBackground(MessagePresenter... params) {
           params[0].saveMessage(message);

           return null;
        }
    }
}
