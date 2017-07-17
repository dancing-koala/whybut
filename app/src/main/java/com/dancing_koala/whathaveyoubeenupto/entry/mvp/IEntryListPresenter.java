package com.dancing_koala.whathaveyoubeenupto.entry.mvp;

/**
 * Created by dancing_koala on 16/07/17.
 */

public interface IEntryListPresenter {
    void attachView(IEntryListView view);

    void detachView();

    void loadActiveEntries();
}
