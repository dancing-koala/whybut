package com.dancing_koala.whathaveyoubeenupto.data.mapper;

import com.dancing_koala.whathaveyoubeenupto.data.dao.TagEntity;
import com.dancing_koala.whathaveyoubeenupto.data.model.Tag;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class TagDaoMapper extends BaseMapper<Tag, TagEntity> {

    @Override
    public Tag entityToModel(TagEntity entity) {
        if (entity == null) {
            return null;
        }

        Tag model = new Tag(entity.getId(), entity.getSlug(), entity.getLabel(), entity.getColor());
        model.setCreated(entity.getCreated());

        return model;
    }

    @Override
    public TagEntity modelToEntity(Tag model) {
        if (model == null) {
            return null;
        }

        TagEntity entity = new TagEntity();
        entity.setId(model.getId());
        entity.setSlug(model.getSlug());
        entity.setLabel(model.getLabel());
        entity.setColor(model.getColor());
        entity.setCreated(model.getCreated());

        return entity;
    }
}
