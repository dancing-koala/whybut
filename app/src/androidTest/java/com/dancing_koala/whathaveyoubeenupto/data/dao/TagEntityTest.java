package com.dancing_koala.whathaveyoubeenupto.data.dao;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.dancing_koala.whathaveyoubeenupto.data.dao.TagEntity;
import com.dancing_koala.whathaveyoubeenupto.data.dao.TagEntityDao;

public class TagEntityTest extends AbstractDaoTestLongPk<TagEntityDao, TagEntity> {

    private static final String BASE_SLUG = "slug_";
    private static final String BASE_LABEL = "label #";

    private static int instanceCount = 0;

    public TagEntityTest() {
        super(TagEntityDao.class);
    }

    @Override
    protected TagEntity createEntity(Long key) {
        instanceCount++;

        TagEntity entity = new TagEntity();
        entity.setId(key);
        entity.setSlug(BASE_SLUG + instanceCount);
        entity.setLabel(BASE_LABEL + instanceCount);
        entity.setColor(0);
        entity.setCreated(System.currentTimeMillis());
        return entity;
    }

}
