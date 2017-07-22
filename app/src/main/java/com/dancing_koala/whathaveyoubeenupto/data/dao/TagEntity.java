package com.dancing_koala.whathaveyoubeenupto.data.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dancing_koala on 04/07/17.
 */

@Entity(nameInDb = "TAG")
public class TagEntity {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String slug;

    @NotNull
    private String label;

    private int color;

    private long created;

    @Generated(hash = 1335948209)
    public TagEntity(Long id, @NotNull String slug, @NotNull String label,
            int color, long created) {
        this.id = id;
        this.slug = slug;
        this.label = label;
        this.color = color;
        this.created = created;
    }

    @Generated(hash = 2114918181)
    public TagEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getCreated() {
        return this.created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
