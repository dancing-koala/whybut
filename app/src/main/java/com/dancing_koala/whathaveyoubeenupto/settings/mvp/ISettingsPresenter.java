package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

/**
 * Created by dancing_koala on 14/07/17.
 */

public interface ISettingsPresenter {
    void attachView(ISettingsView view);

    void detachView();

    void onDeleteAllEntriesClicked();

    void onDeleteAllEntriesConfirmed();

    void onEntryLifespanChanged(int lifespanInDays);
}
