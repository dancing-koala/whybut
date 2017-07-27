package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

/**
 * Created by dancing_koala on 08/07/17.
 */

public abstract class BaseSettingsRepository {

    protected SettingsManager mSettingsManager;

    public BaseSettingsRepository(SettingsManager settingsManager) {
        mSettingsManager = settingsManager;
    }
}
