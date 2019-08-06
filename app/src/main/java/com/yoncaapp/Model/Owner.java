package com.yoncaapp.Model;
public class Owner
{
    private String id;

    private boolean is_verified;

    private String profile_pic_url;

    private String username;

    private boolean blocked_by_viewer;

    private boolean followed_by_viewer;

    private String full_name;

    private boolean has_blocked_viewer;

    private boolean is_private;

    private boolean is_unpublished;

    private boolean requested_by_viewer;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setIs_verified(boolean is_verified){
        this.is_verified = is_verified;
    }
    public boolean getIs_verified(){
        return this.is_verified;
    }
    public void setProfile_pic_url(String profile_pic_url){
        this.profile_pic_url = profile_pic_url;
    }
    public String getProfile_pic_url(){
        return this.profile_pic_url;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setBlocked_by_viewer(boolean blocked_by_viewer){
        this.blocked_by_viewer = blocked_by_viewer;
    }
    public boolean getBlocked_by_viewer(){
        return this.blocked_by_viewer;
    }
    public void setFollowed_by_viewer(boolean followed_by_viewer){
        this.followed_by_viewer = followed_by_viewer;
    }
    public boolean getFollowed_by_viewer(){
        return this.followed_by_viewer;
    }
    public void setFull_name(String full_name){
        this.full_name = full_name;
    }
    public String getFull_name(){
        return this.full_name;
    }
    public void setHas_blocked_viewer(boolean has_blocked_viewer){
        this.has_blocked_viewer = has_blocked_viewer;
    }
    public boolean getHas_blocked_viewer(){
        return this.has_blocked_viewer;
    }
    public void setIs_private(boolean is_private){
        this.is_private = is_private;
    }
    public boolean getIs_private(){
        return this.is_private;
    }
    public void setIs_unpublished(boolean is_unpublished){
        this.is_unpublished = is_unpublished;
    }
    public boolean getIs_unpublished(){
        return this.is_unpublished;
    }
    public void setRequested_by_viewer(boolean requested_by_viewer){
        this.requested_by_viewer = requested_by_viewer;
    }
    public boolean getRequested_by_viewer(){
        return this.requested_by_viewer;
    }
}
