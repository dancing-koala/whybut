package com.dancing_koala.whathaveyoubeenupto.splashscreen.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;

/**
 * Created by dancing_koala on 29/07/17.
 */

public interface ISplashscreenView extends MvpView {
    void canNavigateToNextScreen();

    void displayTasksStartedIndicator();
}
