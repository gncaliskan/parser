package com.instagram_parser.Model;

public class Display_resources
{
    private String src;

    private int config_width;

    private int config_height;

    public void setSrc(String src){
        this.src = src;
    }
    public String getSrc(){
        return this.src;
    }
    public void setConfig_width(int config_width){
        this.config_width = config_width;
    }
    public int getConfig_width(){
        return this.config_width;
    }
    public void setConfig_height(int config_height){
        this.config_height = config_height;
    }
    public int getConfig_height(){
        return this.config_height;
    }
}
