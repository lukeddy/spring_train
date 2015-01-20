package com.tangzq;

public class SayingRepresentation {
    private long id;
    private String content;

    public SayingRepresentation(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
