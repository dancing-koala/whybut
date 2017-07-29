package com.dancing_koala.whathaveyoubeenupto.settings;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by dancing_koala on 14/07/17.
 */

public class Settings {

    public static final String ENTRY_LIFESPAN = "entry_lifespan";
    public static final int DEFAULT_ENTRY_LIFESPAN = 14;

    public static final String DAYS_OF_WEEK = "days_of_week";
    public static final String DEFAULT_DAYS_OF_WEEK = "[1,1,1,1,1,0,0]";

    public static final String REMINDER_TIME_TEMPLATE = "reminder_%d_time";
    public static final String DEFAULT_REMINDER_TIME = "14:00";

    public static final String REMINDER_ENABLED_TEMPLATE = "reminder_%d_enabled";
    public static final boolean DEFAULT_REMINDER_ENABLED = false;

    public static String getReminderKey(String template, long id) {
        return String.format(template, id);
    }

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
