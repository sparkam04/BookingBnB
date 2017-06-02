package com.netcracker.edu.project.Test;


public class TestEntity {
    private String id;
    private String content;

    public TestEntity() {
    }

    public TestEntity(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
