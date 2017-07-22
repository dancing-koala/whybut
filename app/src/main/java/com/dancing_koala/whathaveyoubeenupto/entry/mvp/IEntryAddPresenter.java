package com.dancing_koala.whathaveyoubeenupto.entry.mvp;

/**
 * Created by dancing_koala on 17/07/17.
 */

public interface IEntryAddPresenter {

    void attachView(IEntryAddView view);

    void detachView();

    void createEntry(String content);

    boolean validateContent(String content);

    void onCancelCreation();
}
