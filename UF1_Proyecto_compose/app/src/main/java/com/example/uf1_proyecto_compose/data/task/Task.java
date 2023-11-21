package com.example.uf1_proyecto_compose.data.task;

import java.util.UUID;


public class Task {
    private final String uid;
    private boolean isDone;
    private String title;
    private String description;

    public Task(String title, boolean isDone, String description) {
        this.uid = createUUID(title);
        this.title = title;
        this.isDone = isDone;
        this.description = description;
    }

    private String createUUID(String value) {

        final String stringStamp = System.currentTimeMillis() + value;

        return UUID.fromString(stringStamp).toString();

    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
