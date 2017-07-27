package com.dancing_koala.whathaveyoubeenupto.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;
import com.dancing_koala.whathaveyoubeenupto.reminder.receiver.ReminderReceiver;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dancing_koala on 23/07/17.
 */

public class ReminderManager {

    private static final long DAY_DURATION_IN_MILLIS = 24L * 60L * 60L* 1000L;

    private static final String REMINDER_ALARM_ACTION = "com.dancing_koala.whathaveyoubeenupto.reminder.action.REMINDER_ALARM";

    private AlarmManager mAlarmManager;
    private Context mContext;

    public ReminderManager(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    }

    public void updateReminder(Reminder reminder) {
        unregisterReminder(reminder);

        if (reminder.isEnabled()) {
            registerReminder(reminder);
        }
    }

    public void registerReminders(List<Reminder> reminders) {
        if (reminders == null) {
            return;
        }

        for (Reminder reminder : reminders) {
            registerReminder(reminder);
        }
    }

    private void registerReminder(Reminder reminder) {
        PendingIntent reminderIntent = PendingIntent.getBroadcast(mContext, reminder.hashCode(), createIntent(reminder), 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, reminder.getHourOfDay());
        calendar.set(Calendar.MINUTE, reminder.getMinuteOfDay());
        calendar.set(Calendar.SECOND, 0);

        long targetMillis = calendar.getTimeInMillis();

        if(targetMillis < System.currentTimeMillis()) {
            // If time of the reminder is already passed,
            // we add 24h to time to prevent being notified right away
            targetMillis += DAY_DURATION_IN_MILLIS;
        }

        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetMillis, AlarmManager.INTERVAL_DAY, reminderIntent);
    }

    private void unregisterReminder(Reminder reminder) {
        PendingIntent reminderIntent = PendingIntent.getBroadcast(mContext, reminder.hashCode(), createIntent(reminder), PendingIntent.FLAG_CANCEL_CURRENT);

        mAlarmManager.cancel(reminderIntent);
    }

    private Intent createIntent(Reminder reminder) {
        Intent result = new Intent(mContext, ReminderReceiver.class);
        result.setAction(REMINDER_ALARM_ACTION);
        result.putExtra(ReminderReceiver.EXTRA_REMINDER_ID, reminder.getId());

        return result;
    }
}
