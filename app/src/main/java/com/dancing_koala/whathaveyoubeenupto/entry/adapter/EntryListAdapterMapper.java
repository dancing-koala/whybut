package com.dancing_koala.whathaveyoubeenupto.entry.adapter;

import com.dancing_koala.whathaveyoubeenupto.application.utils.DateTimeUtils;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dancing_koala on 16/07/17.
 */

public class EntryListAdapterMapper {

    private final String mTodayLabel;
    private final String mYesterdayLabel;

    public EntryListAdapterMapper(String todayLabel, String yesterdayLabel) {
        mTodayLabel = todayLabel;
        mYesterdayLabel = yesterdayLabel;
    }

    public EntryListAdapter.Item modelToItem(Entry model) {
        if (model == null) {
            return null;
        }

        long created = model.getCreated();

        String date = DateTimeUtils.getDateFromTimestamp(created);
        String time = DateTimeUtils.getTimeFromTimestamp(created);

        if (DateTimeUtils.isToday(created)) {
            date = mTodayLabel;
        } else if (DateTimeUtils.isYesterday(created)) {
            date = mYesterdayLabel;
        }

        return new EntryListAdapter.Item(model.getId(), date, model.getContent(), time);
    }

    public List<EntryListAdapter.Item> modelsToItems(List<Entry> models) {
        List<EntryListAdapter.Item> items = new ArrayList<>();
        String lastDate = "N/A";

        if (models != null) {
            for (Entry model : models) {
                EntryListAdapter.Item item = modelToItem(model);

                if (!lastDate.equals(item.getDate())) {
                    lastDate = item.getDate();
                    item.setShowDateHeader(true);
                }

                items.add(item);
            }
        }

        return items;
    }
}
