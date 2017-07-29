package com.dancing_koala.whathaveyoubeenupto.settings.mvp;

import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

/**
 * Created by dancing_koala on 15/07/17.
 */

public class SettingsPresenter implements ISettingsPresenter {

    private SettingsManager mSettingsManager;
    private ISettingsView mSettingsView;
    private EntryRepository mEntryRepository;

    private int mCurrentEntryLifespan;

    public SettingsPresenter(SettingsManager settingsManager, EntryRepository entryRepository) {
        mSettingsManager = settingsManager;
        mEntryRepository = entryRepository;
        mCurrentEntryLifespan = settingsManager.getEntryLifespan();
    }

    @Override
    public void attachView(ISettingsView view) {
        mSettingsView = view;
        mSettingsView.displayCurrentEntryLifespan(mCurrentEntryLifespan);
    }

    @Override
    public void detachView() {
        mSettingsView = null;
    }

    @Override
    public void onDeleteAllEntriesClicked() {
        if (mSettingsView != null) {
            mSettingsView.displayDeleteAllEntriesConfirmation();
        }
    }

    @Override
    public void onDeleteAllEntriesConfirmed() {
        mEntryRepository.deleteAll();

        if (mSettingsView != null) {
            mSettingsView.displayDeletionDoneMessage();
        }
    }

    @Override
    public void onEntryLifespanChanged(int lifespanInDays) {
        mSettingsManager.setEntryLifespan(lifespanInDays);
    }
}
