package com.yoncaapp.Model;

import java.util.List;
public class Edge_threaded_comments
{
    private int count;

    private Page_info page_info;

    private List<Edges> edges;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setPage_info(Page_info page_info){
        this.page_info = page_info;
    }
    public Page_info getPage_info(){
        return this.page_info;
    }

    public List<Edges> getEdges() {
        return edges;
    }

    public void setEdges(List<Edges> edges) {
        this.edges = edges;
    }
}
