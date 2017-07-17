package com.dancing_koala.whathaveyoubeenupto.data.mapper;

import com.dancing_koala.whathaveyoubeenupto.data.dao.EntryEntity;
import com.dancing_koala.whathaveyoubeenupto.data.model.Entry;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class EntryDaoMapper extends BaseMapper<Entry, EntryEntity> {

    private static final String DEFAULT_JSON_ARRAY = "[]";

    @Override
    public Entry entityToModel(EntryEntity entity) {
        if (entity == null) {
            return null;
        }

        Entry model = new Entry(entity.getId(), entity.getContent());
        try {
            model.setTagIds(jsonIntArrayToJava(entity.getTagIds()));
        } catch (JSONException e) {
            model.setTagIds(new int[0]);
            e.printStackTrace();
        }
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
        try {
            entity.setTagIds(javaIntArrayToJson(model.getTagIds()));
        } catch (JSONException e) {
            e.printStackTrace();
            entity.setTagIds(DEFAULT_JSON_ARRAY);
        }
        entity.setDayOfYear(model.getDayOfYear());
        entity.setArchived(model.getArchived());
        entity.setCreated(model.getCreated());

        return entity;
    }

    private static int[] jsonIntArrayToJava(String jsonArray) throws JSONException {

        JSONArray parsedJsonArray = new JSONArray(jsonArray);
        int[] result = new int[parsedJsonArray.length()];

        for (int i = 0; i < parsedJsonArray.length(); i++) {
            result[i] = parsedJsonArray.getInt(i);
        }

        return result;
    }

    private static String javaIntArrayToJson(int[] intArray) throws JSONException {

        JSONArray jsonArray = new JSONArray();

        for (int val : intArray) {
            jsonArray.put(val);
        }

        return jsonArray.toString();
    }
}
