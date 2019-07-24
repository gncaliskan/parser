package com.instagram_parser.Model;
import java.util.ArrayList;
import java.util.List;
public class Edge_media_preview_like
{
    private int count;

    private List<String> edges;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setEdges(List<String> edges){
        this.edges = edges;
    }
    public List<String> getEdges(){
        return this.edges;
    }
}