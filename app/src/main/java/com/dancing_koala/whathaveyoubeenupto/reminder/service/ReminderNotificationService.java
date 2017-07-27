package com.dancing_koala.whathaveyoubeenupto.reminder.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.entry.EntryAddActivity;
import com.dancing_koala.whathaveyoubeenupto.reminder.receiver.ReminderReceiver;

/**
 * Created by dancing_koala on 23/07/17.
 */

public class ReminderNotificationService extends IntentService {

    private static final String NAME = "ReminderNotificationService";

    public ReminderNotificationService() {
        super(NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        long id = intent.getLongExtra(ReminderReceiver.EXTRA_REMINDER_ID, -1L);
        String title = getString(R.string.app_name);

        sendNotification(id, title);

        ReminderReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(long id, String title) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent addEntryIntent = PendingIntent.getActivity(this, 0, createAddEntryIntent(), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new android.support.v7.app.NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(getString(R.string.notification_content))
                .setContentIntent(addEntryIntent);

        notificationManager.notify(Long.valueOf(id).hashCode(), builder.build());
    }

    private Intent createAddEntryIntent() {
        return new Intent(this, EntryAddActivity.class);
    }
}
