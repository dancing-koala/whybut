package com.dancing_koala.whathaveyoubeenupto.data.mapper;

import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntity;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dancing_koala on 14/07/17.
 */
public class EntryDaoMapperTest {

    private EntryDaoMapper mEntryDaoMapper;

    @Before
    public void setUp() {
        mEntryDaoMapper = new EntryDaoMapper();
    }

    @Test
    public void entityToModel() throws Exception {

        Long id = 1L;
        String content = "Lorem Ipsum Dolor Sit Amet";
        long created = System.currentTimeMillis();
        Long archived = created + 2000L;
        String dayOfYear = "2017-07-16";


        EntryEntity entity = new EntryEntity(
                id,
                content,
                dayOfYear,
                created,
                archived
        );

        Entry model = mEntryDaoMapper.entityToModel(entity);

        assertTrue(id == model.getId());
        assertEquals(content, model.getContent());
        assertEquals(dayOfYear, model.getDayOfYear());
        assertTrue(created == model.getCreated());
        assertTrue(archived == model.getArchived().longValue());
    }

    @Test
    public void modelToEntity() throws Exception {
        Long id = 1L;
        String content = "Lorem Ipsum Dolor Sit Amet";
        long created = System.currentTimeMillis();
        Long archived = created + 2000L;


        Entry model = new Entry(id, content);
        model.setCreated(created);
        model.setArchived(archived);

        EntryEntity entity = mEntryDaoMapper.modelToEntity(model);

        assertTrue(id == entity.getId().longValue());
        assertEquals(content, entity.getContent());
        assertTrue(created == entity.getCreated());
        assertTrue(archived == entity.getArchived().longValue());
    }
}