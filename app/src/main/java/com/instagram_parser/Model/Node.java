package com.instagram_parser.Model;

import java.util.List;

public class Node
{
    private String __typename;

    private String id;

    private String text;

    private String shortcode;

    private Dimensions dimensions;

    private String gating_info;

    private String media_preview;

    private String display_url;

    private List<Display_resources> display_resources;

    private String accessibility_caption;

    private boolean is_video;

    private boolean should_log_client_event;

    private String tracking_token;

    private Edge_media_to_tagged_user edge_media_to_tagged_user;

    private Owner owner;

    public void set__typename(String __typename){
        this.__typename = __typename;
    }
    public String get__typename(){
        return this.__typename;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setShortcode(String shortcode){
        this.shortcode = shortcode;
    }
    public String getShortcode(){
        return this.shortcode;
    }
    public void setDimensions(Dimensions dimensions){
        this.dimensions = dimensions;
    }
    public Dimensions getDimensions(){
        return this.dimensions;
    }
    public void setGating_info(String gating_info){
        this.gating_info = gating_info;
    }
    public String getGating_info(){
        return this.gating_info;
    }
    public void setMedia_preview(String media_preview){
        this.media_preview = media_preview;
    }
    public String getMedia_preview(){
        return this.media_preview;
    }
    public void setDisplay_url(String display_url){
        this.display_url = display_url;
    }
    public String getDisplay_url(){
        return this.display_url;
    }
    public void setDisplay_resources(List<Display_resources> display_resources){
        this.display_resources = display_resources;
    }
    public List<Display_resources> getDisplay_resources(){
        return this.display_resources;
    }
    public void setAccessibility_caption(String accessibility_caption){
        this.accessibility_caption = accessibility_caption;
    }
    public String getAccessibility_caption(){
        return this.accessibility_caption;
    }
    public void setIs_video(boolean is_video){
        this.is_video = is_video;
    }
    public boolean getIs_video(){
        return this.is_video;
    }
    public void setShould_log_client_event(boolean should_log_client_event){
        this.should_log_client_event = should_log_client_event;
    }
    public boolean getShould_log_client_event(){
        return this.should_log_client_event;
    }
    public void setTracking_token(String tracking_token){
        this.tracking_token = tracking_token;
    }
    public String getTracking_token(){
        return this.tracking_token;
    }
    public void setEdge_media_to_tagged_user(Edge_media_to_tagged_user edge_media_to_tagged_user){
        this.edge_media_to_tagged_user = edge_media_to_tagged_user;
    }
    public Edge_media_to_tagged_user getEdge_media_to_tagged_user(){
        return this.edge_media_to_tagged_user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
