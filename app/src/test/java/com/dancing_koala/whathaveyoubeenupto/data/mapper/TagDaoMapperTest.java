package com.dancing_koala.whathaveyoubeenupto.data.mapper;

import com.dancing_koala.whathaveyoubeenupto.data.dao.TagEntity;
import com.dancing_koala.whathaveyoubeenupto.data.model.Tag;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dancing_koala on 14/07/17.
 */
public class TagDaoMapperTest {

    private TagDaoMapper mTagDaoMapper;

    @Before
    public void setUp() {
        mTagDaoMapper = new TagDaoMapper();
    }

    @Test
    public void entityToModel() throws Exception {
        Long id = 1L;
        String label = "Lorem ipsum";
        String slug = "lorem_ipsum";
        int color = 45;
        long created = System.currentTimeMillis();

        TagEntity entity = new TagEntity(
                id,
                slug,
                label,
                color,
                created
        );

        Tag model = mTagDaoMapper.entityToModel(entity);

        assertTrue(id == model.getId());
        assertEquals(slug, model.getSlug());
        assertEquals(label, model.getLabel());
        assertTrue(color == model.getColor());
        assertTrue(created == model.getCreated());
    }

    @Test
    public void modelToEntity() throws Exception {
        Long id = 1L;
        String label = "Lorem ipsum";
        String slug = "lorem_ipsum";
        int color = 45;
        long created = System.currentTimeMillis();

        Tag model = new Tag(id, slug, label, color);
        model.setCreated(created);

        TagEntity entity = mTagDaoMapper.modelToEntity(model);

        assertTrue(id == entity.getId().longValue());
        assertEquals(slug, entity.getSlug());
        assertEquals(label, entity.getLabel());
        assertTrue(color == entity.getColor());
        assertTrue(created == entity.getCreated());
    }
}