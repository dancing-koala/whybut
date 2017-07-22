package com.dancing_koala.whathaveyoubeenupto.data.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dancing_koala on 08/07/17.
 */

public abstract class BaseMapper<M, E> {

    public List<M> entitiesToModels(List<E> entities) {
        List<M> models = new ArrayList<>();

        if (entities != null) {
            for (E entity : entities) {
                models.add(entityToModel(entity));
            }
        }

        return models;
    }

    public List<E> modelsToEntities(List<M> models) {
        List<E> entities = new ArrayList<>();

        if (models != null) {
            for (M model : models) {
                entities.add(modelToEntity(model));
            }
        }

        return entities;
    }

    public abstract M entityToModel(E entity);

    public abstract E modelToEntity(M model);
}
