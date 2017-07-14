package com.dancing_koala.whathaveyoubeenupto.data.dao;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

public class EntryEntityTest extends AbstractDaoTestLongPk<EntryEntityDao, EntryEntity> {

    private static final String BASE_CONTENT = "content #";

    private static int instanceCount = 0;

    public EntryEntityTest() {
        super(EntryEntityDao.class);
    }

    @Override
    protected EntryEntity createEntity(Long key) {
        instanceCount++;
        EntryEntity entity = new EntryEntity();
        entity.setId(key);
        entity.setContent(BASE_CONTENT + instanceCount);
        entity.setArchived(null);
        entity.setCreated(System.currentTimeMillis());
        return entity;
    }
}
