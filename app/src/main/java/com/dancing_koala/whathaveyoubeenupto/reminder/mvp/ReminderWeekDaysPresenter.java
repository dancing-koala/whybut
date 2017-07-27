package com.dancing_koala.whathaveyoubeenupto.reminder.mvp;

import com.dancing_koala.whathaveyoubeenupto.data.repository.WeekDaysRepository;

/**
 * Created by dancing_koala on 27/07/17.
 */

public class ReminderWeekDaysPresenter implements IReminderWeekDaysPresenter {

    private WeekDaysRepository mRepository;
    private IReminderWeekDaysView mView;

    private boolean[] mWeekDays;

    public ReminderWeekDaysPresenter(WeekDaysRepository repository) {
        mRepository = repository;
        mWeekDays = new boolean[7];
    }

    @Override
    public void attachView(IReminderWeekDaysView view) {
        mView = view;
        mView.displayWeekDays(mWeekDays);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadWeekDays() {
        mWeekDays = mRepository.findWeekDays();

        if (mView != null) {
            mView.displayWeekDays(mWeekDays);
        }
    }

    @Override
    public void onWeekDayStateChanged(int index, boolean state) {
        mWeekDays[index] = state;
        mRepository.updateWeekDays(mWeekDays);
    }
}
