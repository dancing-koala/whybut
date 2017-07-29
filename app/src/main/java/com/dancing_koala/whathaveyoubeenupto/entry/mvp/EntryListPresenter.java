package com.dancing_koala.whathaveyoubeenupto.entry.mvp;

import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;
import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dancing_koala on 16/07/17.
 */

public class EntryListPresenter implements IEntryListPresenter {

    private IEntryListView mView;
    private List<Entry> mEntryList;
    private EntryRepository mRepository;

    public EntryListPresenter(EntryRepository repository) {
        mRepository = repository;
        mEntryList = new ArrayList<>();
    }

    @Override
    public void attachView(IEntryListView view) {
        mView = view;
        mView.displayEntries(mEntryList);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadActiveEntries() {
        Observable.create(new ObservableOnSubscribe<List<Entry>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Entry>> e) throws Exception {
                e.onNext(mRepository.findAllActiveEntries());
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Entry>>() {
                    @Override
                    public void accept(List<Entry> entries) throws Exception {
                        mEntryList = entries;

                        if (mView != null) {
                            mView.displayEntries(entries);
                        }
                    }
                });
    }
}
