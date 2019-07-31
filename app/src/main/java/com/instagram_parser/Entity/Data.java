package com.instagram_parser.Entity;

public class Data
{
    private String id;

    private User user;

    private Images images;

    private String created_time;

    private Caption caption;

    private Likes likes;

    private Comments comments;

    private String link;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
    public void setImages(Images images){
        this.images = images;
    }
    public Images getImages(){
        return this.images;
    }
    public void setCreated_time(String created_time){
        this.created_time = created_time;
    }
    public String getCreated_time(){
        return this.created_time;
    }
    public void setCaption(Caption caption){
        this.caption = caption;
    }
    public Caption getCaption(){
        return this.caption;
    }
    public void setLikes(Likes likes){
        this.likes = likes;
    }
    public Likes getLikes(){
        return this.likes;
    }
    public void setComments(Comments comments){
        this.comments = comments;
    }
    public Comments getComments(){
        return this.comments;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }
}
