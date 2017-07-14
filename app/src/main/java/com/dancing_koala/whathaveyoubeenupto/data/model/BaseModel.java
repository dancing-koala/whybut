package com.dancing_koala.whathaveyoubeenupto.data.model;

/**
 * Created by dancing_koala on 08/07/17.
 */

public abstract class BaseModel {

    protected long mId;
    protected long mCreated;

    public BaseModel(long id) {
        mId = id;
        mCreated = 0L;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getCreated() {
        return mCreated;
    }

    public void setCreated(long created) {
        mCreated = created;
    }
}
