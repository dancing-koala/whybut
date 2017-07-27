package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;

/**
 * Created by dancing_koala on 14/07/17.
 */

public interface ISettingsEditorView extends MvpView {
    void displayDaysOfWeekStates(boolean[] daysOfWeekStates);
}
