package com.instagram_parser.Model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String id;
    private String ownerName;
    private String commentText;
    private String picUrl;
    private long created;
    private boolean isActive;

    public Comment() {
    }

    public Comment(String id, String ownerName, String commentText, String picUrl, long created, boolean isActive) {
        this.id = id;
        this.ownerName = ownerName;
        this.commentText = commentText;
        this.picUrl = picUrl;
        this.created = created;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
