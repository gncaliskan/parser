package com.instagram_parser.Model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String id;
    private String ownerName;
    private String ownerId;
    private String commentText;
    private String picUrl;
    private long created;
    private boolean isActive;
    private boolean isNotDeleted;
    private boolean isAccepted;
    private boolean isContainLabelCount;
    private boolean isMatched;

    public Comment() {
    }

    public Comment(String id, String ownerName, String ownerId, String commentText, String picUrl, long created) {
        this.id = id;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.commentText = commentText;
        this.picUrl = picUrl;
        this.created = created;
        this.isNotDeleted = true;
        this.isContainLabelCount = true;
        this.isAccepted =true;
        this.isActive = true;
        this.isMatched = true;
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

    public boolean isNotDeleted() {
        return isNotDeleted;
    }

    public void setNotDeleted(boolean notDeleted) {
        isNotDeleted = notDeleted;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isContainLabelCount() {
        return isContainLabelCount;
    }

    public void setContainLabelCount(boolean containLabelCount) {
        isContainLabelCount = containLabelCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
