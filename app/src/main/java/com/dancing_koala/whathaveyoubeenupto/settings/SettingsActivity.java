package com.dancing_koala.whathaveyoubeenupto.settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.settings.mvp.ISettingsEditorView;
import com.dancing_koala.whathaveyoubeenupto.settings.mvp.SettingsPresenter;

public class SettingsActivity extends AppCompatActivity implements ISettingsEditorView {

    private ToggleButton[] mDaysOfWeekButtons;
    private SettingsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.title_activity_settings));

//        ButterKnife.bind(this);
//
        initActionbar();
//        findAndStoreDaysOfWeekButtons();
//
//        mPresenter = new SettingsPresenter(SettingsManager.getInstance());
//        mPresenter.attachView(this);
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void findAndStoreDaysOfWeekButtons() {
        LinearLayout daysOfWeekWrapper = (LinearLayout) findViewById(R.id.days_of_week_container);

        final int childCount = daysOfWeekWrapper.getChildCount();
        mDaysOfWeekButtons = new ToggleButton[childCount];

        for (int i = 0; i < childCount; i++) {
            mDaysOfWeekButtons[i] = (ToggleButton) daysOfWeekWrapper.getChildAt(i);
            final int index = i;
            mDaysOfWeekButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.setDayOfWeekState(index, ((ToggleButton) v).isChecked());
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.attachView(this);
    }

    @Override
    public void displayDaysOfWeekStates(boolean[] daysOfWeekStates) {
        for (int i = 0; i < mDaysOfWeekButtons.length; i++) {
            mDaysOfWeekButtons[i].setChecked(daysOfWeekStates[i]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
