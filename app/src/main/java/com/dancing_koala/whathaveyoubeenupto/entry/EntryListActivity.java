package com.dancing_koala.whathaveyoubeenupto.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryListActivity extends AppCompatActivity implements IEntryListView {

    public static final int ENTRY_ADD_REQUEST_CODE = 200;

    @BindView(R.id.entry_list)
    public ListView mEntryListView;
    @BindView(R.id.add_entry_fab)
    public FloatingActionButton mAddEntryFab;

    private EntryListAdapter mAdapter;
    private EntryListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);

        ButterKnife.bind(this);

        mAdapter = new EntryListAdapter(this);
        mEntryListView.setAdapter(mAdapter);

        mAddEntryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEntryAddDialog();
            }
        });

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

    private void openEntryAddDialog() {
        Intent entryAddIntent = new Intent(this, EntryAddActivity.class);
        startActivityForResult(entryAddIntent, ENTRY_ADD_REQUEST_CODE);
    }

    // TODO remove this dev-only method
    private void populateDbWithRandomData() {
        String[] contents = new String[]{
                "content with small length",
                "Bigger length content but not too big, see the next size",
                "Biggest content ever with a lot words, punctuation like ':!?' or even '<>/*-'. If you were looking for data placeholder, here you go !",
                "Not so big content\nBut with line breaks\nSeems useful to me",
        };

        List<Entry> tmp = new ArrayList<>();
        Random random = new Random();
        long now = System.currentTimeMillis();

        for (int i = 0; i < 150; i++) {
            Entry entry = new Entry(i, contents[random.nextInt(contents.length)]);
            entry.setCreated(now);
            entry.setTagIds(new int[0]);

            now -= 4 * 74 * 60 * 1000;

            tmp.add(entry);
        }

        EntryRepository repository = new EntryRepository(((WhybutApp) getApplication()).getDaoSession());
        for (Entry entry : tmp) {
            repository.createEntry(entry);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
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
        mAdapter.updateEntryList(EntryListAdapterMapper.modelsToItems(entryList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ENTRY_ADD_REQUEST_CODE && resultCode == EntryAddActivity.RESULT_ENTRY_ADDED) {
            mPresenter.loadActiveEntries();
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
