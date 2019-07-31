package com.instagram_parser.Entity;
public class From
{
    private String id;

    private String full_name;

    private String profile_picture;

    private String username;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setFull_name(String full_name){
        this.full_name = full_name;
    }
    public String getFull_name(){
        return this.full_name;
    }
    public void setProfile_picture(String profile_picture){
        this.profile_picture = profile_picture;
    }
    public String getProfile_picture(){
        return this.profile_picture;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
}