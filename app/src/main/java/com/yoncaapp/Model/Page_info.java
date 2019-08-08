package com.yoncaapp.Model;

public class Page_info
{
    private boolean has_next_page;

    private String end_cursor;

    public void setHas_next_page(boolean has_next_page){
        this.has_next_page = has_next_page;
    }
    public boolean getHas_next_page(){
        return this.has_next_page;
    }
    public void setEnd_cursor(String end_cursor){
        this.end_cursor = end_cursor;
    }
    public String getEnd_cursor(){
        return this.end_cursor;
    }
}


