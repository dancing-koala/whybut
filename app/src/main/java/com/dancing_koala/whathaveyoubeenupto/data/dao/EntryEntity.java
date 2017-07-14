package com.dancing_koala.whathaveyoubeenupto.data.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dancing_koala on 04/07/17.
 */

@Entity(nameInDb = "ENTRY")
public class EntryEntity {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String content;

    private String tagIds;

    private long created;

    private Long archived;

    @Generated(hash = 1266164316)
    public EntryEntity(Long id, @NotNull String content, String tagIds,
            long created, Long archived) {
        this.id = id;
        this.content = content;
        this.tagIds = tagIds;
        this.created = created;
        this.archived = archived;
    }

    @Generated(hash = 681417959)
    public EntryEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTagIds() {
        return this.tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public long getCreated() {
        return this.created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public Long getArchived() {
        return this.archived;
    }

    public void setArchived(Long archived) {
        this.archived = archived;
    }
}
