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

        mDaysOfWeekStates = mSettingsManager.getDaysOfWeek();

        mReminder1State = mSettingsManager.isReminder1Enabled();
        mReminder1Time = mSettingsManager.getReminder1Time();

        mReminder2State = mSettingsManager.isReminder2Enabled();
        mReminder2Time = mSettingsManager.getReminder2Time();

        mReminder3State = mSettingsManager.isReminder3Enabled();
        mReminder3Time = mSettingsManager.getReminder3Time();
    }

    @Override
    public void attachView(ISettingsEditorView view) {
        mSettingsEditorView = view;
        mSettingsEditorView.displayDaysOfWeekStates(mDaysOfWeekStates);
        mSettingsEditorView.displayReminder1State(mReminder1State, mReminder1Time);
        mSettingsEditorView.displayReminder2State(mReminder2State, mReminder2Time);
        mSettingsEditorView.displayReminder3State(mReminder3State, mReminder3Time);
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
        mSettingsManager.setReminder1Enabled(enabled);
    }

    @Override
    public void setReminder1Time(String time) {
        mReminder1Time = time;
        mSettingsManager.setReminder1Time(time);
    }

    @Override
    public void setReminder2State(boolean enabled) {
        mReminder2State = enabled;
        mSettingsManager.setReminder2Enabled(enabled);
    }

    @Override
    public void setReminder2Time(String time) {
        mReminder2Time = time;
        mSettingsManager.setReminder2Time(time);
    }

    @Override
    public void setReminder3State(boolean enabled) {
        mReminder3State = enabled;
        mSettingsManager.setReminder3Enabled(enabled);
    }

    @Override
    public void setReminder3Time(String time) {
        mReminder3Time = time;
        mSettingsManager.setReminder3Time(time);
    }

    @Override
    public void onEditReminder1TimeClicked() {
        String[] timeUnits = mReminder1Time.split(":");
        mSettingsEditorView.displayReminder1TimeEditor(Integer.parseInt(timeUnits[0]), Integer.parseInt(timeUnits[1]));
    }

    @Override
    public void onEditReminder2TimeClicked() {
        String[] timeUnits = mReminder2Time.split(":");
        mSettingsEditorView.displayReminder2TimeEditor(Integer.parseInt(timeUnits[0]), Integer.parseInt(timeUnits[1]));
    }

    @Override
    public void onEditReminder3TimeClicked() {
        String[] timeUnits = mReminder3Time.split(":");
        mSettingsEditorView.displayReminder3TimeEditor(Integer.parseInt(timeUnits[0]), Integer.parseInt(timeUnits[1]));
    }
}
