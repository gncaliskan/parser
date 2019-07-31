package com.instagram_parser.Entity;

import java.util.List;
public class Root
{
    private Pagination pagination;

    private List<Data> data;

    private Meta meta;

    public void setPagination(Pagination pagination){
        this.pagination = pagination;
    }
    public Pagination getPagination(){
        return this.pagination;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setMeta(Meta meta){
        this.meta = meta;
    }
    public Meta getMeta(){
        return this.meta;
    }
}