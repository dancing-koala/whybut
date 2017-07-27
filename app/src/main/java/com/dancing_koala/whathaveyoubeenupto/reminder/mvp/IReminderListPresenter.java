package com.dancing_koala.whathaveyoubeenupto.reminder.mvp;

/**
 * Created by dancing_koala on 25/07/17.
 */

public interface IReminderListPresenter {

    void attachView(IReminderListView view);

    void detachView();

    void loadReminders();

    void onOpenReminderTimeEditorClicked(long id);

    void updateReminderTime(long id, int hour, int minute);

    void toggleReminderEnabled(long id, boolean enabled);
}
