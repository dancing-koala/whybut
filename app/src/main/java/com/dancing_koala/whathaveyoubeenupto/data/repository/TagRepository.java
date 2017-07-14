package com.dancing_koala.whathaveyoubeenupto.data.repository;


import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoSession;
import com.dancing_koala.whathaveyoubeenupto.data.dao.TagEntity;
import com.dancing_koala.whathaveyoubeenupto.data.dao.TagEntityDao;
import com.dancing_koala.whathaveyoubeenupto.data.mapper.TagDaoMapper;
import com.dancing_koala.whathaveyoubeenupto.data.model.Tag;

import org.greenrobot.greendao.Property;

import java.util.List;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class TagRepository extends BaseRepository {

    private TagDaoMapper mTagDaoMapper;

    public TagRepository(DaoSession daoSession) {
        super(daoSession);
        mTagDaoMapper = new TagDaoMapper();
    }

    public List<Tag> findAllTags() {
        TagEntityDao projectDao = mDaoSession.getTagEntityDao();

        List<TagEntity> projectEntities = projectDao
                .queryBuilder()
                .orderAsc(TagEntityDao.Properties.Label)
                .list();

        return mTagDaoMapper.entitiesToModels(projectEntities);
    }

    private Tag findTagByProperty(Property prop, Object propValue) {
        TagEntityDao projectDao = mDaoSession.getTagEntityDao();

        List<TagEntity> projectEntities = projectDao
                .queryBuilder()
                .where(prop.eq(propValue))
                .list();

        if (projectEntities.size() == 0) {
            return null;
        }

        return mTagDaoMapper.entityToModel(projectEntities.get(0));
    }

    public Tag findTagById(long id) {
        return findTagByProperty(TagEntityDao.Properties.Id, id);
    }

    public Tag findTagBySlug(String slug) {
        return findTagByProperty(TagEntityDao.Properties.Slug, slug);
    }

    public Tag findTagByLabel(String label) {
        return findTagByProperty(TagEntityDao.Properties.Label, label);
    }
}
