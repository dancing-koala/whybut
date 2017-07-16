package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

/**
 * Created by dancing_koala on 14/07/17.
 */

public interface ISettingsPresenter {
    void attachView(ISettingsEditorView view);

    void detachView();

    void setDayOfWeekState(int index, boolean state);

    void setReminder1State(boolean enabled);

    void setReminder1Time(String time);

    void setReminder2State(boolean enabled);

    void setReminder2Time(String time);

    void setReminder3State(boolean enabled);

    void setReminder3Time(String time);

    void onEditReminder1TimeClicked();

    void onEditReminder2TimeClicked();

    void onEditReminder3TimeClicked();
}
