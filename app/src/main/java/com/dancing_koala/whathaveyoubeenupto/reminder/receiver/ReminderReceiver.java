package com.dancing_koala.whathaveyoubeenupto.reminder.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.dancing_koala.whathaveyoubeenupto.data.repository.IWeekDaysRepository;
import com.dancing_koala.whathaveyoubeenupto.data.repository.WeekDaysRepository;
import com.dancing_koala.whathaveyoubeenupto.reminder.service.ReminderNotificationService;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

import java.util.Calendar;

/**
 * Created by dancing_koala on 23/07/17.
 */

public class ReminderReceiver extends WakefulBroadcastReceiver {

    public static final String EXTRA_REMINDER_ID = "ReminderReceiver.extra.REMINDER_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();

        IWeekDaysRepository repository = new WeekDaysRepository(SettingsManager.getInstance());
        boolean[] daysOfWeek = repository.findWeekDays();
        int currentDayIndex = calendarDayOfWeekToIndex(calendar.get(Calendar.DAY_OF_WEEK));

        if(!daysOfWeek[currentDayIndex]) {
            return;
        }

        Intent notifIntent = new Intent(context, ReminderNotificationService.class);
        notifIntent.putExtra(EXTRA_REMINDER_ID, intent.getLongExtra(EXTRA_REMINDER_ID, -1L));

        startWakefulService(context, notifIntent);
    }

    private int calendarDayOfWeekToIndex(int calendarDow) {
        int index = calendarDow - 2;

        return (index < 0) ? 6 : index;
    }
}
