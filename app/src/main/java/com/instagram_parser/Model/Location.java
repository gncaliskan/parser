package com.instagram_parser.Model;
public class Location
{
    private String id;

    private boolean has_public_page;

    private String name;

    private String slug;

    private String address_json;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setHas_public_page(boolean has_public_page){
        this.has_public_page = has_public_page;
    }
    public boolean getHas_public_page(){
        return this.has_public_page;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSlug(String slug){
        this.slug = slug;
    }
    public String getSlug(){
        return this.slug;
    }
    public void setAddress_json(String address_json){
        this.address_json = address_json;
    }
    public String getAddress_json(){
        return this.address_json;
    }
}
