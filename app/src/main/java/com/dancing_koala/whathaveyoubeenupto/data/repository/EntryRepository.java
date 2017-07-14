package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoSession;
import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntity;
import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntityDao;
import com.dancing_koala.whathaveyoubeenupto.data.mapper.EntryDaoMapper;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import org.greenrobot.greendao.Property;

import java.util.List;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class EntryRepository extends BaseRepository {

    private EntryDaoMapper mEntryDaoMapper;

    public EntryRepository(DaoSession daoSession) {
        super(daoSession);
        mEntryDaoMapper = new EntryDaoMapper();
    }

    public List<Entry> findAllEntriesOrderedByNameAsc() {
        EntryEntityDao userDao = mDaoSession.getEntryEntityDao();

        List<EntryEntity> userEntities = userDao
                .queryBuilder()
                .orderDesc(EntryEntityDao.Properties.Created)
                .list();

        return mEntryDaoMapper.entitiesToModels(userEntities);
    }

    private Entry findEntryByProperty(Property prop, Object propValue) {
        EntryEntityDao userDao = mDaoSession.getEntryEntityDao();

        List<EntryEntity> userEntities = userDao
                .queryBuilder()
                .where(prop.eq(propValue))
                .list();

        if (userEntities.size() == 0) {
            return null;
        }

        return mEntryDaoMapper.entityToModel(userEntities.get(0));
    }

    public Entry findEntryById(long id) {
        return findEntryByProperty(EntryEntityDao.Properties.Id, id);
    }
}
