package com.dancing_koala.whathaveyoubeenupto.entry.mvp;

import android.util.Log;

import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;
import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dancing_koala on 17/07/17.
 */

public class EntryAddPresenter implements IEntryAddPresenter {

    private IEntryAddView mView;
    private EntryRepository mRepository;

    public EntryAddPresenter(EntryRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attachView(IEntryAddView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void createEntry(final String content) {
        Observable.create(new ObservableOnSubscribe<Entry>() {
            @Override
            public void subscribe(ObservableEmitter<Entry> e) throws Exception {
                Entry entry = new Entry(0L, content);
                entry.setCreated(-1L);

                long newId = mRepository.createEntry(entry);

                if (newId > 0L) {
                    e.onNext(mRepository.findEntryById(newId));
                } else {
                    e.onError(new Throwable());
                }

                e.onComplete();
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Entry>() {
                    @Override
                    public void accept(Entry entry) throws Exception {
                        mView.displayCreationSuccessMessage();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.displayCreationFailureMessage();
                    }
                });
    }

    @Override
    public boolean validateContent(String content) {
        return removeAllBlankChars(content).length() > 0;
    }

    private String removeAllBlankChars(String target) {
        return target.replaceAll("[\n\r\t ]", "");
    }

    @Override
    public void onCancelCreation() {
        if (mView != null) {
            mView.close();
        }
    }
}
