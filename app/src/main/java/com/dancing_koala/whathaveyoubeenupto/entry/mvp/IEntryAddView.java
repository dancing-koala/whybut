package com.dancing_koala.whathaveyoubeenupto.entry.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;

/**
 * Created by dancing_koala on 17/07/17.
 */

public interface IEntryAddView extends MvpView {
    void close();

    void displayCreationSuccessMessage();

    void displayCreationFailureMessage();
}
