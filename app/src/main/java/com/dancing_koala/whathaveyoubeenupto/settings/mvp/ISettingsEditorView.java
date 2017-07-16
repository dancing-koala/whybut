package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;

/**
 * Created by dancing_koala on 14/07/17.
 */

public interface ISettingsEditorView extends MvpView {
    void displayDaysOfWeekStates(boolean[] daysOfWeekStates);

    void displayReminder1State(boolean enabled, String time);

    void displayReminder2State(boolean enabled, String time);

    void displayReminder3State(boolean enabled, String time);

    void displayReminder1TimeEditor(int hours, int minutes);

    void displayReminder2TimeEditor(int hours, int minutes);

    void displayReminder3TimeEditor(int hours, int minutes);
}
