package com.dancing_koala.whathaveyoubeenupto.reminder.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;
import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;

import java.util.List;

/**
 * Created by dancing_koala on 25/07/17.
 */

public interface IReminderWeekDaysView extends MvpView {

    void displayWeekDays(boolean[] weekDays);
}
