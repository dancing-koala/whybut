package com.dancing_koala.whathaveyoubeenupto.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.WhybutApp;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;
import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;
import com.dancing_koala.whathaveyoubeenupto.entry.adapter.EntryListAdapter;
import com.dancing_koala.whathaveyoubeenupto.entry.adapter.EntryListAdapterMapper;
import com.dancing_koala.whathaveyoubeenupto.entry.mvp.EntryListPresenter;
import com.dancing_koala.whathaveyoubeenupto.entry.mvp.IEntryListView;
import com.dancing_koala.whathaveyoubeenupto.reminder.ReminderListActivity;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryListActivity extends AppCompatActivity implements IEntryListView {

    public static final int ENTRY_ADD_REQUEST_CODE = 0x123;
    public static final int SETTINGS_REQUEST_CODE = 0x234;

    @BindView(R.id.entry_list)
    public ListView mEntryList;

    @BindView(R.id.placeholder)
    public View mPlaceholder;

    private EntryListAdapter mAdapter;
    private EntryListAdapterMapper mAdapterMapper;
    private EntryListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        setTitle(getString(R.string.title_activity_entry_list));

        ButterKnife.bind(this);

        mAdapterMapper = new EntryListAdapterMapper(getString(R.string.label_today), getString(R.string.label_yesterday));

        mAdapter = new EntryListAdapter(this);
        mEntryList.setAdapter(mAdapter);

        mPresenter = new EntryListPresenter(new EntryRepository(((WhybutApp) getApplication()).getDaoSession()));
        mPresenter.loadActiveEntries();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @OnClick(R.id.add_entry_fab)
    public void openEntryAddDialog() {
        Intent entryAddIntent = new Intent(this, EntryAddActivity.class);
        startActivityForResult(entryAddIntent, ENTRY_ADD_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_reminders:
                Intent remindersIntent = new Intent(this, ReminderListActivity.class);
                startActivity(remindersIntent);
                return true;

            case R.id.menu_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
                return true;

            case R.id.menu_quit:
                finish();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayEntries(List<Entry> entryList) {
        togglePlaceholder(entryList.isEmpty());
        mAdapter.updateEntryList(mAdapterMapper.modelsToItems(entryList));
    }

    private void togglePlaceholder(boolean show) {
        mPlaceholder.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ENTRY_ADD_REQUEST_CODE && resultCode == EntryAddActivity.RESULT_ENTRY_ADDED
                || requestCode == SETTINGS_REQUEST_CODE && resultCode == SettingsActivity.RESULT_ENTRIES_ALTERED) {
            mPresenter.loadActiveEntries();
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
