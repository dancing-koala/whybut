package com.dancing_koala.whathaveyoubeenupto.data.model;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class Reminder extends BaseModel {

    private String mTimeOfDay;
    private boolean mEnabled;

    public Reminder(long id, String timeOfDay) {
        super(id);
        mTimeOfDay = timeOfDay;
    }

    public String getTimeOfDay() {
        return mTimeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        mTimeOfDay = timeOfDay;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    public int getHourOfDay() {
        return Integer.parseInt(mTimeOfDay.split(":")[0]);
    }

    public int getMinuteOfDay() {
        return Integer.parseInt(mTimeOfDay.split(":")[1]);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "mId=" + mId +
                ", mTimeOfDay='" + mTimeOfDay + '\'' +
                ", enabled='" + mEnabled + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Long.valueOf(mId).hashCode();
    }
}
