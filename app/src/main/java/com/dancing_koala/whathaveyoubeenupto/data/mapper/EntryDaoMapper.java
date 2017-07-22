package com.dancing_koala.whathaveyoubeenupto.data.mapper;

import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntity;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class EntryDaoMapper extends BaseMapper<Entry, EntryEntity> {

    @Override
    public Entry entityToModel(EntryEntity entity) {
        if (entity == null) {
            return null;
        }

        Entry model = new Entry(entity.getId(), entity.getContent());
        model.setDayOfYear(entity.getDayOfYear());
        model.setArchived(entity.getArchived());
        model.setCreated(entity.getCreated());

        return model;
    }

    @Override
    public EntryEntity modelToEntity(Entry model) {
        if (model == null) {
            return null;
        }

        EntryEntity entity = new EntryEntity();
        entity.setId(model.getId());
        entity.setContent(model.getContent());
        entity.setDayOfYear(model.getDayOfYear());
        entity.setArchived(model.getArchived());
        entity.setCreated(model.getCreated());

        return entity;
    }
}
