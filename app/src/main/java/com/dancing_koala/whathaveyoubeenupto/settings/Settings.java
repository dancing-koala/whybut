package com.dancing_koala.whathaveyoubeenupto.settings;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by dancing_koala on 14/07/17.
 */

public class Settings {
    public static final String WHYBUT_ENABLED = "whybut_enabled";
    public static final boolean DEFAULT_WHYBUT_ENABLED = false;

    public static final String DAYS_OF_WEEK = "days_of_week";
    public static final String DEFAULT_DAYS_OF_WEEK = "[1,1,1,1,1,0,0]";

    // Times
    public static final String TIME_REMINDER_1 = "time_reminder_1";
    public static final String TIME_REMINDER_2 = "time_reminder_2";
    public static final String TIME_REMINDER_3 = "time_reminder_3";

    public static final String DEFAULT_TIME_REMINDER_1 = "08:00";
    public static final String DEFAULT_TIME_REMINDER_2 = "13:00";
    public static final String DEFAULT_TIME_REMINDER_3 = "18:00";

    // Notifications
    public static final String REMINDER_1_ENABLED = "reminder_1_enabled";
    public static final String REMINDER_2_ENABLED = "reminder_2_enabled";
    public static final String REMINDER_3_ENABLED = "reminder_3_enabled";

    public static final boolean DEFAULT_REMINDER_1_ENABLED = true;
    public static final boolean DEFAULT_REMINDER_2_ENABLED = true;
    public static final boolean DEFAULT_REMINDER_3_ENABLED = true;

    public static boolean[] parseDaysOfWeek(String daysOfWeekJson) {
        boolean[] result = new boolean[7];
        JSONArray daysOfWeekArray;

        try {
            daysOfWeekArray = new JSONArray(daysOfWeekJson);

            for (int i = 0; i < daysOfWeekArray.length(); i++) {
                result[i] = daysOfWeekArray.getInt(i) == 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encodeDaysOfWeek(boolean[] daysOfWeek) {
        JSONArray result = new JSONArray();

        for (boolean day : daysOfWeek) {
            result.put(day ? 1 : 0);
        }

        return result.toString();
    }
}
