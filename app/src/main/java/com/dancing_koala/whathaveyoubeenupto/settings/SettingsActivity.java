package com.dancing_koala.whathaveyoubeenupto.settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;
import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.utils.DateTimeUtils;
import com.dancing_koala.whathaveyoubeenupto.settings.mvp.ISettingsEditorView;
import com.dancing_koala.whathaveyoubeenupto.settings.mvp.SettingsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements ISettingsEditorView {

    public static final String TIMEPICKER_DIALOG_FRAGMENT_TAG = TimePickerDialogFragment.class.getName();

    @BindView(R.id.reminder_1_switch)
    public Switch mReminder1Switch;
    @BindView(R.id.reminder_1_time)
    public TextView mReminder1Time;

    @BindView(R.id.reminder_2_switch)
    public Switch mReminder2Switch;
    @BindView(R.id.reminder_2_time)
    public TextView mReminder2Time;

    @BindView(R.id.reminder_3_switch)
    public Switch mReminder3Switch;
    @BindView(R.id.reminder_3_time)
    public TextView mReminder3Time;

    private ToggleButton[] mDaysOfWeekButtons;
    private SettingsPresenter mPresenter;

    private RadialTimePickerDialogFragment mTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.settings_activity_title));

        ButterKnife.bind(this);

        initActionbar();
        findAndStoreDaysOfWeekButtons();
        initRemindersStateListeners();
        initTimePickerDialog();

        mPresenter = new SettingsPresenter(SettingsManager.getInstance());
        mPresenter.attachView(this);
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initTimePickerDialog() {
        mTimePickerDialog = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                        Toast.makeText(SettingsActivity.this, hourOfDay + "-" + minute, Toast.LENGTH_SHORT).show();
                    }
                })
                .setThemeCustom(R.style.BetterPickersDialogs)
                .setDoneText(getString(R.string.ok))
                .setCancelText(getString(R.string.cancel));
    }

    private void findAndStoreDaysOfWeekButtons() {
        LinearLayout daysOfWeekWrapper = (LinearLayout) findViewById(R.id.days_of_week_wrapper);

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

    private void initRemindersStateListeners() {
        mReminder1Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setReminder1State(((Switch) v).isChecked());
            }
        });

        mReminder2Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setReminder2State(((Switch) v).isChecked());
            }
        });

        mReminder3Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setReminder3State(((Switch) v).isChecked());
            }
        });

        findViewById(R.id.reminder_1_time_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onEditReminder1TimeClicked();
            }
        });

        findViewById(R.id.reminder_2_time_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onEditReminder2TimeClicked();
            }
        });

        findViewById(R.id.reminder_3_time_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onEditReminder3TimeClicked();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void displayDaysOfWeekStates(boolean[] daysOfWeekStates) {
        for (int i = 0; i < mDaysOfWeekButtons.length; i++) {
            mDaysOfWeekButtons[i].setChecked(daysOfWeekStates[i]);
        }
    }

    @Override
    public void displayReminder1State(boolean enabled, String time) {
        mReminder1Switch.setChecked(enabled);
        mReminder1Time.setText(time);
    }

    @Override
    public void displayReminder2State(boolean enabled, String time) {
        mReminder2Switch.setChecked(enabled);
        mReminder2Time.setText(time);
    }

    @Override
    public void displayReminder3State(boolean enabled, String time) {
        mReminder3Switch.setChecked(enabled);
        mReminder3Time.setText(time);
    }

    @Override
    public void displayReminder1TimeEditor(int hours, final int minutes) {
        mTimePickerDialog.setStartTime(hours, minutes);
        mTimePickerDialog.setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                String formattedTime = DateTimeUtils.formatTime(hourOfDay, minute);
                mPresenter.setReminder1Time(formattedTime);
                mReminder1Time.setText(formattedTime);
            }
        });

        mTimePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void displayReminder2TimeEditor(int hours, int minutes) {
        mTimePickerDialog.setStartTime(hours, minutes);
        mTimePickerDialog.setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                String formattedTime = DateTimeUtils.formatTime(hourOfDay, minute);
                mPresenter.setReminder2Time(formattedTime);
                mReminder2Time.setText(formattedTime);
            }
        });

        mTimePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void displayReminder3TimeEditor(int hours, int minutes) {
        mTimePickerDialog.setStartTime(hours, minutes);
        mTimePickerDialog.setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                String formattedTime = DateTimeUtils.formatTime(hourOfDay, minute);
                mPresenter.setReminder3Time(formattedTime);
                mReminder3Time.setText(formattedTime);
            }
        });

        mTimePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_DIALOG_FRAGMENT_TAG);
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
