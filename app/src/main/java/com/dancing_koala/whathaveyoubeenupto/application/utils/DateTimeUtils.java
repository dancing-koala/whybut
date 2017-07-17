package com.dancing_koala.whathaveyoubeenupto.application.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dancing_koala on 16/07/17.
 */

public class DateTimeUtils {

    public static final String DATABASE_DATE_TEMPLATE = "yyyy-MM-dd";

    private static SimpleDateFormat mDateFormat;
    private static SimpleDateFormat mDatabaseDateFormat;
    private static Calendar mCalendar;

    public static void init(String format) {
        mDateFormat = new SimpleDateFormat(format);
        mDatabaseDateFormat = new SimpleDateFormat(DATABASE_DATE_TEMPLATE);
        mCalendar = Calendar.getInstance();
    }

    public static String getDateFromTimestamp(long timestamp) {
        mCalendar.setTimeInMillis(timestamp);

        return mDateFormat.format(mCalendar.getTime());
    }

    public static String getDatabaseDateFromTimestamp(long timestamp) {
        mCalendar.setTimeInMillis(timestamp);

        return mDatabaseDateFormat.format(mCalendar.getTime());
    }

    public static String getTimeFromTimestamp(long timestamp) {
        mCalendar.setTimeInMillis(timestamp);

        int hours = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = mCalendar.get(Calendar.MINUTE);

        return formatTime(hours, minutes);
    }

    public static boolean isToday(long timestamp) {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.MINUTE, 0);

        return timestamp >= mCalendar.getTimeInMillis();
    }

    public static boolean isYesterday(long timestamp) {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.MINUTE, 0);
        long today = mCalendar.getTimeInMillis();
        long yesterday = today - 24L * 60L * 60L * 1000L;

        return timestamp < today && timestamp >= yesterday;
    }


    public static String formatTime(int hours, int minutes) {
        return ((hours > 9) ? "" : "0") + hours + ":" + ((minutes > 9) ? "" : "0") + minutes;
    }
}
