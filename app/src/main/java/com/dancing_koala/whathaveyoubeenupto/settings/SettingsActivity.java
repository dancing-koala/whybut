package com.dancing_koala.whathaveyoubeenupto.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dancing_koala.whathaveyoubeenupto.BuildConfig;
import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.WhybutApp;
import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;
import com.dancing_koala.whathaveyoubeenupto.settings.mvp.ISettingsView;
import com.dancing_koala.whathaveyoubeenupto.settings.mvp.SettingsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements ISettingsView {

    public static final int RESULT_ENTRIES_ALTERED = 0x321;

    @BindView(R.id.delete_task_select)
    public Spinner mDeleteTaskSelect;

    private SettingsPresenter mPresenter;
    private AlertDialog mConfirmationDialog;

    private int[] mEntryLifespans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.title_activity_settings));

        ButterKnife.bind(this);

        initActionbar();
        initDeleteTaskSelect();
        initConfirmationDialog();

        ((TextView) findViewById(R.id.app_version)).setText(getString(R.string.version_format, BuildConfig.VERSION_NAME));

        mPresenter = new SettingsPresenter(
                SettingsManager.getInstance(),
                new EntryRepository(((WhybutApp) getApplication()).getDaoSession())
        );

        mPresenter.attachView(this);
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initConfirmationDialog() {
        mConfirmationDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.danger)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private void initDeleteTaskSelect() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);

        mEntryLifespans = getResources().getIntArray(R.array.settings_tasks_delays);

        for (int option : mEntryLifespans) {
            adapter.add(option);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mDeleteTaskSelect.setAdapter(adapter);

        mDeleteTaskSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onEntryLifespanChanged(mEntryLifespans[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.delete_all_btn)
    public void onDeleteAllEntriesButtonClick() {
        mPresenter.onDeleteAllEntriesClicked();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayDeleteAllEntriesConfirmation() {
        mConfirmationDialog.setMessage(getString(R.string.settings_delete_all_confirm_msg));
        mConfirmationDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.do_it), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onDeleteAllEntriesConfirmed();
                setResult(RESULT_ENTRIES_ALTERED);
            }
        });

        mConfirmationDialog.show();
    }

    @Override
    public void displayDeletionDoneMessage() {
        Toast.makeText(this, R.string.all_done, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayCurrentEntryLifespan(int lifespanInDays) {
        mDeleteTaskSelect.setSelection(getIndexInArray(lifespanInDays, mEntryLifespans));
    }

    private int getIndexInArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
