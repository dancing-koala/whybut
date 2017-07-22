package com.dancing_koala.whathaveyoubeenupto.entry.mvp;

import com.dancing_koala.whathaveyoubeenupto.application.ui.MvpView;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import java.util.List;

/**
 * Created by dancing_koala on 16/07/17.
 */

public interface IEntryListView extends MvpView {
    void displayEntries(List<Entry> entryList);
}
