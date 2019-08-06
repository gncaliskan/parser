package com.yoncaapp.Model;

import java.util.List;
import java.util.Map;

public class CommentList {

    private List<Comment> comments;
    private Map<String, List<Comment>> commentMap;
    private static CommentList instance = null;
    private CommentList() {
    }

    public static CommentList getInstance() {
        if(instance == null) {
            instance = new CommentList();
        }
        return instance;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Map<String, List<Comment>> getCommentMap() {
        return commentMap;
    }

    public void setCommentMap(Map<String, List<Comment>> commentMap) {
        this.commentMap = commentMap;
    }
}
