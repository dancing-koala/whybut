package com.dancing_koala.whathaveyoubeenupto.data.repository;

import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;

import java.util.List;

/**
 * Created by dancing_koala on 27/07/17.
 */

public interface IReminderRepository {
    List<Reminder> findAllReminders();

    List<Reminder> findAllEnabledReminders();

    long createReminder(Reminder reminder);

    long updateReminder(Reminder reminder);
}
