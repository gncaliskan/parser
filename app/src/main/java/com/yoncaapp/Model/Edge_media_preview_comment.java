package com.yoncaapp.Model;
import java.util.List;
public class Edge_media_preview_comment
{
    private int count;

    private List<Edges> edges;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setEdges(List<Edges> edges){
        this.edges = edges;
    }
    public List<Edges> getEdges(){
        return this.edges;
    }
}