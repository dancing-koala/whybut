package com.dancing_koala.whathaveyoubeenupto.data.model;

/**
 * Created by dancing_koala on 08/07/17.
 */

public class Tag extends BaseModel {

    private String mSlug;
    private String mLabel;

    private int mColor;

    public Tag(long id, String slug, String label, int color) {
        super(id);
        mSlug = slug;
        mLabel = label;
        mColor = color;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}
