package com.instagram_parser.Model;
import java.util.ArrayList;
import java.util.List;
public class Shortcode_media
{
    private String __typename;

    private String id;

    private String shortcode;

//    private Dimensions dimensions;

//    private String gating_info;

//    private String media_preview;

  //  private String display_url;

    //private List<Display_resources> display_resources;

    //private String accessibility_caption;

    //private boolean is_video;

    //private boolean should_log_client_event;

    //private String tracking_token;

    private Edge_media_to_tagged_user edge_media_to_tagged_user;

    private Edge_media_to_caption edge_media_to_caption;

    //private boolean caption_is_edited;

    //private boolean has_ranked_comments;

    private Edge_media_to_parent_comment edge_media_to_parent_comment;
    private Edge_media_to_comment edge_media_to_comment;

    private Edge_media_preview_comment edge_media_preview_comment;

    //private boolean comments_disabled;

    //private int taken_at_timestamp;

    private Edge_media_preview_like edge_media_preview_like;

    private Edge_media_to_sponsor_user edge_media_to_sponsor_user;

    //private Location location;

//    private boolean viewer_has_liked;
//
//    private boolean viewer_has_saved;
//
//    private boolean viewer_has_saved_to_collection;
//
//    private boolean viewer_in_photo_of_you;
//
//    private boolean viewer_can_reshare;

    private Owner owner;

   // private boolean is_ad;

    private Edge_web_media_to_related_media edge_web_media_to_related_media;

    public String get__typename() {
        return __typename;
    }

    public void set__typename(String __typename) {
        this.__typename = __typename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public Edge_media_to_tagged_user getEdge_media_to_tagged_user() {
        return edge_media_to_tagged_user;
    }

    public void setEdge_media_to_tagged_user(Edge_media_to_tagged_user edge_media_to_tagged_user) {
        this.edge_media_to_tagged_user = edge_media_to_tagged_user;
    }

    public Edge_media_to_caption getEdge_media_to_caption() {
        return edge_media_to_caption;
    }

    public void setEdge_media_to_caption(Edge_media_to_caption edge_media_to_caption) {
        this.edge_media_to_caption = edge_media_to_caption;
    }

    public Edge_media_to_parent_comment getEdge_media_to_parent_comment() {
        return edge_media_to_parent_comment;
    }

    public void setEdge_media_to_parent_comment(Edge_media_to_parent_comment edge_media_to_parent_comment) {
        this.edge_media_to_parent_comment = edge_media_to_parent_comment;
    }

    public Edge_media_to_comment getEdge_media_to_comment() {
        return edge_media_to_comment;
    }

    public void setEdge_media_to_comment(Edge_media_to_comment edge_media_to_comment) {
        this.edge_media_to_comment = edge_media_to_comment;
    }

    public Edge_media_preview_comment getEdge_media_preview_comment() {
        return edge_media_preview_comment;
    }

    public void setEdge_media_preview_comment(Edge_media_preview_comment edge_media_preview_comment) {
        this.edge_media_preview_comment = edge_media_preview_comment;
    }

    public Edge_media_preview_like getEdge_media_preview_like() {
        return edge_media_preview_like;
    }

    public void setEdge_media_preview_like(Edge_media_preview_like edge_media_preview_like) {
        this.edge_media_preview_like = edge_media_preview_like;
    }

    public Edge_media_to_sponsor_user getEdge_media_to_sponsor_user() {
        return edge_media_to_sponsor_user;
    }

    public void setEdge_media_to_sponsor_user(Edge_media_to_sponsor_user edge_media_to_sponsor_user) {
        this.edge_media_to_sponsor_user = edge_media_to_sponsor_user;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Edge_web_media_to_related_media getEdge_web_media_to_related_media() {
        return edge_web_media_to_related_media;
    }

    public void setEdge_web_media_to_related_media(Edge_web_media_to_related_media edge_web_media_to_related_media) {
        this.edge_web_media_to_related_media = edge_web_media_to_related_media;
    }
}