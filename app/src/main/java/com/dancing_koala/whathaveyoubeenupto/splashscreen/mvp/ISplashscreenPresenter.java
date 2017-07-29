package com.dancing_koala.whathaveyoubeenupto.splashscreen.mvp;

/**
 * Created by dancing_koala on 29/07/17.
 */

public interface ISplashscreenPresenter {

    void attachView(ISplashscreenView view);

    void detachView();

    void executeTasks();
}
