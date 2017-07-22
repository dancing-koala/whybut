package com.dancing_koala.whathaveyoubeenupto.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dancing_koala on 14/07/17.
 */

public class SettingsManager {

    private static SettingsManager instance;
    private final SharedPreferences mSharedPreferences;

    public static SettingsManager getInstance() {
        return instance;
    }

    public static void initialize(Context context, String preferencesFilename) {
        instance = new SettingsManager(context, preferencesFilename);
    }

    private SettingsManager(Context context, String preferencesFilename) {
        mSharedPreferences = context.getSharedPreferences(preferencesFilename, Context.MODE_PRIVATE);
    }

    private boolean readSetting(String settingName, boolean defaultValue) {
        return mSharedPreferences.getBoolean(settingName, defaultValue);
    }

    private void editSetting(String settingName, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(settingName, value);
        editor.commit();
    }

    private String readSetting(String settingName, String defaultValue) {
        return mSharedPreferences.getString(settingName, defaultValue);
    }

    private void editSetting(String settingName, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(settingName, value);
        editor.commit();
    }

    public boolean isWhybutEnabled() {
        return readSetting(Settings.WHYBUT_ENABLED, Settings.DEFAULT_WHYBUT_ENABLED);
    }

    public void setWhybutEnabled(boolean enabled) {
        editSetting(Settings.WHYBUT_ENABLED, enabled);
    }

    public boolean[] getDaysOfWeek() {
        return Settings.parseDaysOfWeek(readSetting(Settings.DAYS_OF_WEEK, Settings.DEFAULT_DAYS_OF_WEEK));
    }

    public void setDaysOfWeek(boolean[] daysOfWeek) {
        editSetting(Settings.DAYS_OF_WEEK, Settings.encodeDaysOfWeek(daysOfWeek));
    }

    public String getReminder1Time() {
        return readSetting(Settings.TIME_REMINDER_1, Settings.DEFAULT_TIME_REMINDER_1);
    }

    public void setReminder1Time(String time) {
        editSetting(Settings.TIME_REMINDER_1, time);
    }

    public String getReminder2Time() {
        return readSetting(Settings.TIME_REMINDER_2, Settings.DEFAULT_TIME_REMINDER_2);
    }

    public void setReminder2Time(String time) {
        editSetting(Settings.TIME_REMINDER_2, time);
    }

    public String getReminder3Time() {
        return readSetting(Settings.TIME_REMINDER_3, Settings.DEFAULT_TIME_REMINDER_3);
    }

    public void setReminder3Time(String time) {
        editSetting(Settings.TIME_REMINDER_3, time);
    }

    public boolean isReminder1Enabled() {
        return readSetting(Settings.REMINDER_1_ENABLED, Settings.DEFAULT_REMINDER_1_ENABLED);
    }

    public void setReminder1Enabled(boolean enabled) {
        editSetting(Settings.REMINDER_1_ENABLED, enabled);
    }

    public boolean isReminder2Enabled() {
        return readSetting(Settings.REMINDER_2_ENABLED, Settings.DEFAULT_REMINDER_2_ENABLED);
    }

    public void setReminder2Enabled(boolean enabled) {
        editSetting(Settings.REMINDER_2_ENABLED, enabled);
    }

    public boolean isReminder3Enabled() {
        return readSetting(Settings.REMINDER_3_ENABLED, Settings.DEFAULT_REMINDER_3_ENABLED);
    }

    public void setReminder3Enabled(boolean enabled) {
        editSetting(Settings.REMINDER_3_ENABLED, enabled);
    }
}
