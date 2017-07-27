package com.dancing_koala.whathaveyoubeenupto.reminder.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;
import com.dancing_koala.whathaveyoubeenupto.data.repository.ReminderRepository;
import com.dancing_koala.whathaveyoubeenupto.reminder.ReminderManager;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

import java.util.List;

/**
 * Created by dancing_koala on 23/07/17.
 */

public class ReminderBootReceiver extends BroadcastReceiver {

    public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            return;
        }

        ReminderRepository repository = new ReminderRepository(SettingsManager.getInstance());

        List<Reminder> reminders = repository.findAllEnabledReminders();

        if (reminders == null || reminders.size() == 0) {
            return;
        }

        ReminderManager manager = new ReminderManager(context);
        manager.registerReminders(reminders);

    }
}
