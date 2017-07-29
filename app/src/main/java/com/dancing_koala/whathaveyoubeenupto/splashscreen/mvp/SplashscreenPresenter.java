package com.dancing_koala.whathaveyoubeenupto.splashscreen.mvp;

import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dancing_koala on 29/07/17.
 */

public class SplashscreenPresenter implements ISplashscreenPresenter {

    private static final long MILLIS_IN_A_DAY = 24L * 60L * 60L * 1000L;

    private EntryRepository mRepository;
    private SettingsManager mSettingsManager;

    private ISplashscreenView mView;

    public SplashscreenPresenter(EntryRepository repository, SettingsManager settingsManager) {
        mRepository = repository;
        mSettingsManager = settingsManager;
    }

    @Override
    public void attachView(ISplashscreenView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void executeTasks() {
        mView.displayTasksStartedIndicator();

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                int days = mSettingsManager.getEntryLifespan();
                final long limit = System.currentTimeMillis() - (days * MILLIS_IN_A_DAY);

                mRepository.deleteAllEntriesCreatedBefore(limit);

                e.onNext(true);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                mView.canNavigateToNextScreen();
            }
        });
    }
}
