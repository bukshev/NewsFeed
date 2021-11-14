package ru.nsu.fit.mobdev.model;

import java.util.Date;
import java.util.UUID;

public final class Article {

    private final UUID uuid;
    private final String title;
    private final Date createdDate;
    private final String content;

    public Article(
            final UUID uuid,
            final String title,
            final Date createdDate,
            final String content
    ) {
        this.uuid = uuid;
        this.title = title;
        this.createdDate = createdDate;
        this.content = content;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getContent() {
        return content;
    }
}
