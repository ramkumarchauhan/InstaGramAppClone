package com.example.instagramappclone;

public class Post {
    private String content;
    private int imageResource;

    public Post(String content, int imageResource) {
        this.content = content;
        this.imageResource = imageResource;
    }

    public String getContent() {
        return content;
    }

    public int getImageResource() {
        return imageResource;
    }
}
