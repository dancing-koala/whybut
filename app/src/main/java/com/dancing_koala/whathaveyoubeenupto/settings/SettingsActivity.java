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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.title_activity_settings));

        initActionbar();
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void displayDaysOfWeekStates(boolean[] daysOfWeekStates) {

    }
}
