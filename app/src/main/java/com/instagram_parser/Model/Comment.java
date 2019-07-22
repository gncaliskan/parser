package com.instagram_parser.Model;

public class Comment {
    private String id;
    private String ownerName;
    private String commentText;
    private String picUrl;
    private boolean isActive;

    public Comment() {
    }

    public Comment(String id, String ownerName, String commentText, String picUrl, boolean isActive) {
        this.id = id;
        this.ownerName = ownerName;
        this.commentText = commentText;
        this.picUrl = picUrl;
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
}
