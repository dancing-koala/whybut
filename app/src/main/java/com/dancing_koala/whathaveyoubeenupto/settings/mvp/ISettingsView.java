package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;

/**
 * Created by dancing_koala on 14/07/17.
 */

public interface ISettingsView extends MvpView {
    void displayDeleteAllEntriesConfirmation();

    void displayDeletionDoneMessage();

    void displayCurrentEntryLifespan(int lifespanInDays);
}
