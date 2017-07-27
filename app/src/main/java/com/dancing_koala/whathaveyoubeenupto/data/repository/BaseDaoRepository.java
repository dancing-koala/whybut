package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoSession;

/**
 * Created by dancing_koala on 08/07/17.
 */

public abstract class BaseDaoRepository {

    protected DaoSession mDaoSession;

    public BaseDaoRepository(DaoSession daoSession) {
        mDaoSession = daoSession;
    }
}
