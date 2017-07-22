package com.dancing_koala.whathaveyoubeenupto.data.model;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class Entry extends BaseModel {

    private String mContent;
    private String mDayOfYear;

    private Long mArchived;

    public Entry(long id, String content) {
        super(id);
        mContent = content;
        mCreated = -1L;
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

    public String getDayOfYear() {
        return mDayOfYear;
    }

    public void setDayOfYear(String dayOfYear) {
        mDayOfYear = dayOfYear;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "mId=" + mId +
                ", mContent='" + mContent + '\'' +
                ", mCreated=" + mCreated +
                ", mArchived=" + mArchived +
                '}';
    }


}
