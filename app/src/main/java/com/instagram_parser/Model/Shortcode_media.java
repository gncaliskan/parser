package com.instagram_parser.Model;

import java.util.List;

public class Shortcode_media {

        private String __typename;

        private String id;

        private String shortcode;

        private Dimensions dimensions;

        private String gating_info;

        private String media_preview;

        private String display_url;

        private List<Display_resources> display_resources;

        private boolean is_video;

        private boolean should_log_client_event;

        private String tracking_token;

        private Edge_media_to_tagged_user edge_media_to_tagged_user;

        private Edge_media_to_caption edge_media_to_caption;

        private boolean caption_is_edited;

        private boolean has_ranked_comments;

        private Edge_media_to_parent_comment edge_media_to_parent_comment;

        private Edge_media_preview_comment edge_media_preview_comment;

        private boolean comments_disabled;

        private int taken_at_timestamp;

        private Edge_media_preview_like edge_media_preview_like;

        private Edge_media_to_sponsor_user edge_media_to_sponsor_user;

        private String location;

        private boolean viewer_has_liked;

        private boolean viewer_has_saved;

        private boolean viewer_has_saved_to_collection;

        private boolean viewer_in_photo_of_you;

        private boolean viewer_can_reshare;

        private Owner owner;

        private boolean is_ad;

        private Edge_web_media_to_related_media edge_web_media_to_related_media;

        private Edge_sidecar_to_children edge_sidecar_to_children;

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
        public void setEdge_media_to_caption(Edge_media_to_caption edge_media_to_caption){
            this.edge_media_to_caption = edge_media_to_caption;
        }
        public Edge_media_to_caption getEdge_media_to_caption(){
            return this.edge_media_to_caption;
        }
        public void setCaption_is_edited(boolean caption_is_edited){
            this.caption_is_edited = caption_is_edited;
        }
        public boolean getCaption_is_edited(){
            return this.caption_is_edited;
        }
        public void setHas_ranked_comments(boolean has_ranked_comments){
            this.has_ranked_comments = has_ranked_comments;
        }
        public boolean getHas_ranked_comments(){
            return this.has_ranked_comments;
        }
        public void setEdge_media_to_parent_comment(Edge_media_to_parent_comment edge_media_to_parent_comment){
            this.edge_media_to_parent_comment = edge_media_to_parent_comment;
        }
        public Edge_media_to_parent_comment getEdge_media_to_parent_comment(){
            return this.edge_media_to_parent_comment;
        }
        public void setEdge_media_preview_comment(Edge_media_preview_comment edge_media_preview_comment){
            this.edge_media_preview_comment = edge_media_preview_comment;
        }
        public Edge_media_preview_comment getEdge_media_preview_comment(){
            return this.edge_media_preview_comment;
        }
        public void setComments_disabled(boolean comments_disabled){
            this.comments_disabled = comments_disabled;
        }
        public boolean getComments_disabled(){
            return this.comments_disabled;
        }
        public void setTaken_at_timestamp(int taken_at_timestamp){
            this.taken_at_timestamp = taken_at_timestamp;
        }
        public int getTaken_at_timestamp(){
            return this.taken_at_timestamp;
        }
        public void setEdge_media_preview_like(Edge_media_preview_like edge_media_preview_like){
            this.edge_media_preview_like = edge_media_preview_like;
        }
        public Edge_media_preview_like getEdge_media_preview_like(){
            return this.edge_media_preview_like;
        }
        public void setEdge_media_to_sponsor_user(Edge_media_to_sponsor_user edge_media_to_sponsor_user){
            this.edge_media_to_sponsor_user = edge_media_to_sponsor_user;
        }
        public Edge_media_to_sponsor_user getEdge_media_to_sponsor_user(){
            return this.edge_media_to_sponsor_user;
        }
        public void setLocation(String location){
            this.location = location;
        }
        public String getLocation(){
            return this.location;
        }
        public void setViewer_has_liked(boolean viewer_has_liked){
            this.viewer_has_liked = viewer_has_liked;
        }
        public boolean getViewer_has_liked(){
            return this.viewer_has_liked;
        }
        public void setViewer_has_saved(boolean viewer_has_saved){
            this.viewer_has_saved = viewer_has_saved;
        }
        public boolean getViewer_has_saved(){
            return this.viewer_has_saved;
        }
        public void setViewer_has_saved_to_collection(boolean viewer_has_saved_to_collection){
            this.viewer_has_saved_to_collection = viewer_has_saved_to_collection;
        }
        public boolean getViewer_has_saved_to_collection(){
            return this.viewer_has_saved_to_collection;
        }
        public void setViewer_in_photo_of_you(boolean viewer_in_photo_of_you){
            this.viewer_in_photo_of_you = viewer_in_photo_of_you;
        }
        public boolean getViewer_in_photo_of_you(){
            return this.viewer_in_photo_of_you;
        }
        public void setViewer_can_reshare(boolean viewer_can_reshare){
            this.viewer_can_reshare = viewer_can_reshare;
        }
        public boolean getViewer_can_reshare(){
            return this.viewer_can_reshare;
        }
        public void setOwner(Owner owner){
            this.owner = owner;
        }
        public Owner getOwner(){
            return this.owner;
        }
        public void setIs_ad(boolean is_ad){
            this.is_ad = is_ad;
        }
        public boolean getIs_ad(){
            return this.is_ad;
        }
        public void setEdge_web_media_to_related_media(Edge_web_media_to_related_media edge_web_media_to_related_media){
            this.edge_web_media_to_related_media = edge_web_media_to_related_media;
        }
        public Edge_web_media_to_related_media getEdge_web_media_to_related_media(){
            return this.edge_web_media_to_related_media;
        }
        public void setEdge_sidecar_to_children(Edge_sidecar_to_children edge_sidecar_to_children){
            this.edge_sidecar_to_children = edge_sidecar_to_children;
        }
        public Edge_sidecar_to_children getEdge_sidecar_to_children(){
            return this.edge_sidecar_to_children;
        }

}
