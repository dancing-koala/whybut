package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

/**
 * Created by dancing_koala on 15/07/17.
 */

public class SettingsPresenter implements ISettingsPresenter {

    private boolean[] mDaysOfWeekStates;
    private SettingsManager mSettingsManager;
    private ISettingsEditorView mSettingsEditorView;

    private boolean mReminder1State;
    private String mReminder1Time;

    private boolean mReminder2State;
    private String mReminder2Time;

    private boolean mReminder3State;
    private String mReminder3Time;

    public SettingsPresenter(SettingsManager settingsManager) {
        mSettingsManager = settingsManager;

        mDaysOfWeekStates = mSettingsManager.getWeekDays();
    }

    @Override
    public void attachView(ISettingsEditorView view) {
        mSettingsEditorView = view;
        mSettingsEditorView.displayDaysOfWeekStates(mDaysOfWeekStates);
    }

    @Override
    public void detachView() {
        mSettingsEditorView = null;
    }

    @Override
    public void setDayOfWeekState(int index, boolean state) {
        if (index < 0 || index >= mDaysOfWeekStates.length) {
            return;
        }

        mDaysOfWeekStates[index] = state;

        mSettingsManager.setDaysOfWeek(mDaysOfWeekStates);
    }

    @Override
    public void setReminder1State(boolean enabled) {
        mReminder1State = enabled;
    }

    @Override
    public void setReminder1Time(String time) {
        mReminder1Time = time;
    }

    @Override
    public void setReminder2State(boolean enabled) {
        mReminder2State = enabled;
    }

    @Override
    public void setReminder2Time(String time) {
        mReminder2Time = time;
    }

    @Override
    public void setReminder3State(boolean enabled) {
        mReminder3State = enabled;
    }

    @Override
    public void setReminder3Time(String time) {
        mReminder3Time = time;
    }

    @Override
    public void onEditReminder1TimeClicked() {
        String[] timeUnits = mReminder1Time.split(":");
    }

    @Override
    public void onEditReminder2TimeClicked() {
        String[] timeUnits = mReminder2Time.split(":");
    }

    @Override
    public void onEditReminder3TimeClicked() {
        String[] timeUnits = mReminder3Time.split(":");
    }
}
