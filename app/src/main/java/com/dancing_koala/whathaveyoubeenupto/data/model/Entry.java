package com.dancing_koala.whathaveyoubeenupto.data.model;

import java.util.Arrays;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class Entry extends BaseModel {

    private String mContent;

    private Long mArchived;

    private int[] mTagIds;

    public Entry(long id, String content) {
        super(id);
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Long getArchived() {
        return mArchived;
    }

    public void setArchived(Long archived) {
        mArchived = archived;
    }

    public int[] getTagIds() {
        return mTagIds;
    }

    public void setTagIds(int[] tagIds) {
        mTagIds = tagIds;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "mId=" + mId +
                ", mContent='" + mContent + '\'' +
                ", mCreated=" + mCreated +
                ", mArchived=" + mArchived +
                ", mTagIds=" + Arrays.toString(mTagIds) +
                '}';
    }
}
