package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoSession;

/**
 * Created by dancing_koala on 08/07/17.
 */

public abstract class BaseRepository {

    protected DaoSession mDaoSession;

    public BaseRepository(DaoSession daoSession) {
        mDaoSession = daoSession;
    }


}
