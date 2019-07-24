package com.instagram_parser.Model;
public class Node
{
    private String id;

    private String text;

    private int created_at;

    private boolean did_report_as_spam;

    private Owner owner;

    private boolean viewer_has_liked;

    private Edge_liked_by edge_liked_by;

    private Edge_threaded_comments edge_threaded_comments;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    public void setCreated_at(int created_at){
        this.created_at = created_at;
    }
    public int getCreated_at(){
        return this.created_at;
    }
    public void setDid_report_as_spam(boolean did_report_as_spam){
        this.did_report_as_spam = did_report_as_spam;
    }
    public boolean getDid_report_as_spam(){
        return this.did_report_as_spam;
    }
    public void setOwner(Owner owner){
        this.owner = owner;
    }
    public Owner getOwner(){
        return this.owner;
    }
    public void setViewer_has_liked(boolean viewer_has_liked){
        this.viewer_has_liked = viewer_has_liked;
    }
    public boolean getViewer_has_liked(){
        return this.viewer_has_liked;
    }
    public void setEdge_liked_by(Edge_liked_by edge_liked_by){
        this.edge_liked_by = edge_liked_by;
    }
    public Edge_liked_by getEdge_liked_by(){
        return this.edge_liked_by;
    }

    public boolean isDid_report_as_spam() {
        return did_report_as_spam;
    }

    public boolean isViewer_has_liked() {
        return viewer_has_liked;
    }

    public Edge_threaded_comments getEdge_threaded_comments() {
        return edge_threaded_comments;
    }

    public void setEdge_threaded_comments(Edge_threaded_comments edge_threaded_comments) {
        this.edge_threaded_comments = edge_threaded_comments;
    }
}