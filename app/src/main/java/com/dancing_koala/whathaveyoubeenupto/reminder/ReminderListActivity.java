package com.dancing_koala.whathaveyoubeenupto.reminder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;
import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.utils.DateTimeUtils;
import com.dancing_koala.whathaveyoubeenupto.data.model.Reminder;
import com.dancing_koala.whathaveyoubeenupto.data.repository.ReminderRepository;
import com.dancing_koala.whathaveyoubeenupto.data.repository.WeekDaysRepository;
import com.dancing_koala.whathaveyoubeenupto.reminder.mvp.IReminderListView;
import com.dancing_koala.whathaveyoubeenupto.reminder.mvp.IReminderWeekDaysView;
import com.dancing_koala.whathaveyoubeenupto.reminder.mvp.ReminderListPresenter;
import com.dancing_koala.whathaveyoubeenupto.reminder.mvp.ReminderWeekDaysPresenter;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dancing_koala on 25/07/17.
 */

public class ReminderListActivity extends AppCompatActivity implements IReminderListView, IReminderWeekDaysView {

    public static final String TIMEPICKER_DIALOG_FRAGMENT_TAG = TimePickerDialogFragment.class.getName();

    @BindView(R.id.reminders_container)
    LinearLayout mRemindersContainer;

    private LongSparseArray<TextView> mReminderTimeViews;
    private RadialTimePickerDialogFragment mTimePickerDialog;

    private ReminderListPresenter mReminderListPresenter;
    private ReminderWeekDaysPresenter mReminderWeekDaysPresenter;

    private ToggleButton[] mWeekDaysButtons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        ButterKnife.bind(this);

        setTitle(getString(R.string.title_activity_reminder_list));

        mReminderTimeViews = new LongSparseArray<>();

        initActionBar();
        initTimePickerDialog();

        mReminderListPresenter = new ReminderListPresenter(new ReminderRepository(SettingsManager.getInstance()), new ReminderManager(this));
        mReminderWeekDaysPresenter = new ReminderWeekDaysPresenter(new WeekDaysRepository(SettingsManager.getInstance()));

        mReminderWeekDaysPresenter.loadWeekDays();
        mReminderListPresenter.loadReminders();

        findAndStoreDaysOfWeekButtons();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mReminderListPresenter.attachView(this);
        mReminderWeekDaysPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mReminderListPresenter.detachView();
        mReminderWeekDaysPresenter.detachView();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar == null) {
            return;
        }

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void addRemindersToList(List<Reminder> reminders) {
        if (mRemindersContainer == null) {
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);

        for (Reminder reminder : reminders) {
            View reminderView = inflater.inflate(R.layout.item_reminder, mRemindersContainer, false);

            TextView timeView = (TextView) reminderView.findViewById(R.id.reminder_time);
            timeView.setText(reminder.getTimeOfDay());

            final long id = reminder.getId();

            timeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mReminderListPresenter.onOpenReminderTimeEditorClicked(id);
                }
            });

            Switch reminderSwitch = (Switch) reminderView.findViewById(R.id.reminder_switch);

            reminderSwitch.setChecked(reminder.isEnabled());

            reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mReminderListPresenter.toggleReminderEnabled(id, isChecked);
                }
            });

            mReminderTimeViews.put(reminder.getId(), timeView);
            mRemindersContainer.addView(reminderView);
        }
    }

    private void findAndStoreDaysOfWeekButtons() {
        LinearLayout daysOfWeekWrapper = (LinearLayout) findViewById(R.id.days_of_week_container);

        final int childCount = daysOfWeekWrapper.getChildCount();
        mWeekDaysButtons = new ToggleButton[childCount];

        for (int i = 0; i < childCount; i++) {
            mWeekDaysButtons[i] = (ToggleButton) daysOfWeekWrapper.getChildAt(i);
            final int index = i;
            mWeekDaysButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mReminderWeekDaysPresenter.onWeekDayStateChanged(index, ((ToggleButton) v).isChecked());
                }
            });
        }
    }

    private void initTimePickerDialog() {
        mTimePickerDialog = new RadialTimePickerDialogFragment()
                .setThemeCustom(R.style.BetterPickersDialogs)
                .setDoneText(getString(R.string.ok))
                .setCancelText(getString(R.string.cancel));
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
    public void displayReminders(List<Reminder> reminderList) {
        mRemindersContainer.removeAllViews();

        addRemindersToList(reminderList);
    }

    @Override
    public void displayReminderTimeEditor(final long id, int hour, int minute) {
        mTimePickerDialog.setStartTime(hour, minute);
        mTimePickerDialog.setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                mReminderListPresenter.updateReminderTime(id, hourOfDay, minute);
                mReminderTimeViews.get(id).setText(DateTimeUtils.formatTime(hourOfDay, minute));
            }
        });

        mTimePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void displayWeekDays(boolean[] weekDays) {
        for (int i = 0; i < weekDays.length; i++) {
            mWeekDaysButtons[i].setChecked(weekDays[i]);
        }
    }
}
