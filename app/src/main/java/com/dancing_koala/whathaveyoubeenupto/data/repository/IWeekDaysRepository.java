package com.dancing_koala.whathaveyoubeenupto.data.repository;

/**
 * Created by dancing_koala on 27/07/17.
 */

public interface IWeekDaysRepository {
    boolean[] findWeekDays();

    void updateWeekDays(boolean[] weekDays);
}
