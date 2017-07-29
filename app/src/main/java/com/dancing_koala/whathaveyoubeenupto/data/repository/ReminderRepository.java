package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dancing_koala on 23/07/17.
 */

public class ReminderRepository extends BaseSettingsRepository implements IReminderRepository {

    private static final int DEFAULT_REMINDER_COUNT = 3;
    private static final int MIN_REMINDER_COUNT = 1;

    private int mReminderCount;

    public ReminderRepository(SettingsManager settingsManager) {
        this(settingsManager, DEFAULT_REMINDER_COUNT);
    }

    public ReminderRepository(SettingsManager settingsManager, int reminderCount) {
        super(settingsManager);
        mReminderCount = reminderCount > MIN_REMINDER_COUNT ? reminderCount : MIN_REMINDER_COUNT;
    }

    @Override
    public List<Reminder> findAllReminders() {
        List<Reminder> reminderList = new ArrayList<>();

        for (int i = 0; i < mReminderCount; i++) {
            Reminder reminder = new Reminder(i, mSettingsManager.getReminderTime(i));
            reminder.setEnabled(mSettingsManager.isReminderEnabled(i));

            reminderList.add(reminder);
        }

        return reminderList;
    }

    @Override
    public List<Reminder> findAllEnabledReminders() {
        List<Reminder> reminderList = new ArrayList<>();

        for (int i = 0; i < mReminderCount; i++) {
            Reminder reminder = new Reminder(i, mSettingsManager.getReminderTime(i));
            reminder.setEnabled(mSettingsManager.isReminderEnabled(i));

            if (reminder.isEnabled()) {
                reminderList.add(reminder);
            }
        }

        return reminderList;
    }

    @Override
    public long createReminder(Reminder reminder) {
        return saveReminder(reminder);
    }

    @Override
    public long updateReminder(Reminder reminder) {
        return saveReminder(reminder);
    }

    private long saveReminder(Reminder reminder) {
        mSettingsManager.setReminderTime(reminder.getId(), reminder.getTimeOfDay());
        mSettingsManager.setReminderEnabled(reminder.getId(), reminder.isEnabled());

        return reminder.getId();
    }
}
