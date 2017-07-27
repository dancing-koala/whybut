package com.dancing_koala.whathaveyoubeenupto.reminder.mvp;

/**
 * Created by dancing_koala on 25/07/17.
 */

public interface IReminderWeekDaysPresenter {

    void attachView(IReminderWeekDaysView view);

    void detachView();

    void loadWeekDays();

    void onWeekDayStateChanged(int index, boolean state);
}
