package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.application.utils.DateTimeUtils;
import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoSession;
import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntity;
import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntityDao;
import com.dancing_koala.whathaveyoubeenupto.data.mapper.EntryDaoMapper;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.DeleteQuery;

import java.util.List;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class EntryRepository extends BaseDaoRepository {

    private EntryDaoMapper mEntryDaoMapper;

    public EntryRepository(DaoSession daoSession) {
        super(daoSession);
        mEntryDaoMapper = new EntryDaoMapper();
    }

    public List<Entry> findAllActiveEntries() {
        EntryEntityDao entityDao = mDaoSession.getEntryEntityDao();

        List<EntryEntity> userEntities = entityDao
                .queryBuilder()
                .where(EntryEntityDao.Properties.Archived.isNull())
                .orderDesc(EntryEntityDao.Properties.DayOfYear)
                .orderAsc(EntryEntityDao.Properties.Created)
                .list();

        return mEntryDaoMapper.entitiesToModels(userEntities);
    }

    public long createEntry(Entry entry) {
        EntryEntityDao entityDao = mDaoSession.getEntryEntityDao();

        long created = entry.getCreated() > 0L ? entry.getCreated() : System.currentTimeMillis();

        EntryEntity entityToSave = mEntryDaoMapper.modelToEntity(entry);
        entityToSave.setId(null);
        entityToSave.setCreated(created);
        entityToSave.setDayOfYear(DateTimeUtils.getDatabaseDateFromTimestamp(created));

        return entityDao.insert(entityToSave);
    }

    private Entry findEntryByProperty(Property prop, Object propValue) {
        EntryEntityDao entityDao = mDaoSession.getEntryEntityDao();

        List<EntryEntity> userEntities = entityDao
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

    public void deleteAll() {
        EntryEntityDao entityDao = mDaoSession.getEntryEntityDao();
        entityDao.deleteAll();
    }

    public void deleteAllEntriesCreatedBefore(long timestamp) {
        EntryEntityDao entityDao = mDaoSession.getEntryEntityDao();

        DeleteQuery query = entityDao.queryBuilder()
                .where(EntryEntityDao.Properties.Created.lt(timestamp))
                .buildDelete();

        query.executeDeleteWithoutDetachingEntities();

        entityDao.detachAll();
    }
}
