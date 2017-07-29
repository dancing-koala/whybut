package com.dancing_koala.whathaveyoubeenupto.reminder.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.utils.DateTimeUtils;
import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;
import com.dancing_koala.whathaveyoubeenupto.data.repository.IReminderRepository;
import com.dancing_koala.whathaveyoubeenupto.data.repository.ReminderRepository;
import com.dancing_koala.whathaveyoubeenupto.reminder.ReminderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dancing_koala on 27/07/17.
 */

public class ReminderListPresenter implements IReminderListPresenter {

    private IReminderRepository mReminderRepository;
    private IReminderListView mReminderListView;
    private ReminderManager mReminderManager;
    private List<Reminder> mReminderList;

    public ReminderListPresenter(ReminderRepository reminderRepository, ReminderManager reminderManager) {
        mReminderRepository = reminderRepository;
        mReminderList = new ArrayList<>();

        mReminderManager = reminderManager;
    }

    @Override
    public void attachView(IReminderListView view) {
        mReminderListView = view;
        mReminderListView.displayReminders(mReminderList);
    }

    @Override
    public void detachView() {
        mReminderListView = null;
    }

    @Override
    public void loadReminders() {
        mReminderList = mReminderRepository.findAllReminders();

        if (mReminderListView != null) {
            mReminderListView.displayReminders(mReminderList);
        }
    }

    @Override
    public void onOpenReminderTimeEditorClicked(long id) {
        Reminder reminder = getReminderWithId(id);

        if (reminder != null) {
            mReminderListView.displayReminderTimeEditor(id, reminder.getHourOfDay(), reminder.getMinuteOfDay());
        }
    }

    @Override
    public void updateReminderTime(long id, int hour, int minute) {
        Reminder reminder = getReminderWithId(id);
        reminder.setTimeOfDay(DateTimeUtils.formatTime(hour, minute));

        updateReminder(reminder);
    }

    @Override
    public void toggleReminderEnabled(long id, boolean enabled) {
        Reminder reminder = getReminderWithId(id);
        reminder.setEnabled(enabled);

        updateReminder(reminder);
    }

    private void updateReminder(Reminder reminder) {
        mReminderRepository.updateReminder(reminder);
        mReminderManager.updateReminder(reminder);
    }

    private Reminder getReminderWithId(long id) {
        for (Reminder reminder : mReminderList) {
            if (reminder.getId() == id) {
                return reminder;
            }
        }

        return null;
    }
}
