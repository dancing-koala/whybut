package com.dancing_koala.whathaveyoubeenupto.entry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dancing_koala.whathaveyoubeenupto.R;

import java.util.List;

/**
 * Created by dancing_koala on 16/07/17.
 */

public class EntryListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Item> mItemList;

    public EntryListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_entry, parent, false);

            vh = new ViewHolder();
            vh.date = (TextView) convertView.findViewById(R.id.entry_date);
            vh.content = (TextView) convertView.findViewById(R.id.entry_content);
            vh.time = (TextView) convertView.findViewById(R.id.entry_time);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Item item = mItemList.get(position);

        if (item.mustShowDateHeader()) {
            vh.date.setText(item.getDate());
            vh.date.setVisibility(View.VISIBLE);
        } else {
            vh.date.setVisibility(View.GONE);
        }

        vh.content.setText(item.getContent());
        vh.time.setText(item.getTime());

        return convertView;
    }

    public void updateEntryList(List<Item> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    private boolean intInArray(int val, int[] array) {
        for (int item : array) {
            if (item == val) {
                return true;
            }
        }

        return false;
    }

    private static class ViewHolder {
        TextView date;
        TextView content;
        TextView time;
    }

    public static class Item {
        private final long mId;
        private final String mDate;
        private final String mContent;
        private final String mTime;

        private boolean mShowDateHeader;

        public Item(long id, String date, String content, String time) {
            mId = id;
            mDate = date;
            mContent = content;
            mTime = time;
        }

        public long getId() {
            return mId;
        }

        public String getDate() {
            return mDate;
        }

        public String getContent() {
            return mContent;
        }

        public String getTime() {
            return mTime;
        }

        public boolean mustShowDateHeader() {
            return mShowDateHeader;
        }

        public void setShowDateHeader(boolean showDateHeader) {
            mShowDateHeader = showDateHeader;
        }
    }
}
