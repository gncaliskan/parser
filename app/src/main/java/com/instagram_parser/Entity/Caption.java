package com.instagram_parser.Entity;
public class Caption
{
    private String id;

    private String text;

    private String created_time;

    private From from;

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
    public void setCreated_time(String created_time){
        this.created_time = created_time;
    }
    public String getCreated_time(){
        return this.created_time;
    }
    public void setFrom(From from){
        this.from = from;
    }
    public From getFrom(){
        return this.from;
    }
}