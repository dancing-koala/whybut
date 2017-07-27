package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

/**
 * Created by dancing_koala on 23/07/17.
 */

public class WeekDaysRepository extends BaseSettingsRepository implements IWeekDaysRepository {

    public WeekDaysRepository(SettingsManager settingsManager) {
        super(settingsManager);
    }

    @Override
    public boolean[] findWeekDays() {
        return mSettingsManager.getWeekDays();
    }

    @Override
    public void updateWeekDays(boolean[] weekDays) {
        mSettingsManager.setDaysOfWeek(weekDays);
    }
}
