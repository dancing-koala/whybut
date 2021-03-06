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

    private int readSetting(String settingName, int defaultValue) {
        return mSharedPreferences.getInt(settingName, defaultValue);
    }

    private void editSetting(String settingName, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(settingName, value);
        editor.commit();
    }

    public boolean[] getWeekDays() {
        return Settings.parseDaysOfWeek(readSetting(Settings.DAYS_OF_WEEK, Settings.DEFAULT_DAYS_OF_WEEK));
    }

    public void setDaysOfWeek(boolean[] daysOfWeek) {
        editSetting(Settings.DAYS_OF_WEEK, Settings.encodeDaysOfWeek(daysOfWeek));
    }

    public String getReminderTime(long id) {
        return readSetting(Settings.getReminderKey(Settings.REMINDER_TIME_TEMPLATE, id), Settings.DEFAULT_REMINDER_TIME);
    }

    public void setReminderTime(long id, String time) {
        editSetting(Settings.getReminderKey(Settings.REMINDER_TIME_TEMPLATE, id), time);
    }

    public boolean isReminderEnabled(long id) {
        return readSetting(Settings.getReminderKey(Settings.REMINDER_ENABLED_TEMPLATE, id), Settings.DEFAULT_REMINDER_ENABLED);
    }

    public void setReminderEnabled(long id, boolean enabled) {
        editSetting(Settings.getReminderKey(Settings.REMINDER_ENABLED_TEMPLATE, id), enabled);
    }

    public int getEntryLifespan() {
        return readSetting(Settings.ENTRY_LIFESPAN, Settings.DEFAULT_ENTRY_LIFESPAN);
    }

    public void setEntryLifespan(int lifespan) {
        editSetting(Settings.ENTRY_LIFESPAN, lifespan);
    }
}
