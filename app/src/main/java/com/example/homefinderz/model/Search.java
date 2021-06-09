package com.example.homefinderz.model;

public class Search {

    protected int search_id;
    protected int minPrice;
    protected int maxPrice;
    protected String location;
    protected int maxSqm;
    protected int minSqm;
    protected int bedrooms;
    protected int bathrooms;
    protected int floor;
    protected boolean heat;

    public Search(){}
    
    public Search(int search_id, int minPrice, int maxPrice, String location,
                  int maxSqm, int minSqm, int beds, int baths, int floor,
                  boolean heat){
        this.search_id=search_id;
        this.minPrice=minPrice;
        this.maxPrice=maxPrice;
        this.location=location;
        this.maxSqm = maxSqm;
        this.minSqm = minSqm;
        this.bedrooms=beds;
        this.bathrooms=baths;
        this.floor=floor;
        this.heat=heat;
    }
    
    public int getSearchId(){return search_id;}
    public int getMinPrice(){return minPrice;}
    public int getMaxPrice(){return maxPrice;}
    public String getLocation(){return location;}
    public int getMaxSqm(){return maxSqm;}
    public int getMinSqm(){return minSqm;}
    public int getBedrooms(){return bedrooms;}
    public int getBathrooms(){return bathrooms;}
    public int getFloor(){return floor;}
    public boolean getHeat(){return heat;}

    public void setSearchId(int id){this.search_id=id;}
    public void setMinPrice(int minp){this.minPrice=minp;}
    public void setMaxPrice(int maxp){this.maxPrice=maxp;}
    public void setLocation(String loc){this.location=loc;}
    public void setMaxSqm(int maxf){this.maxSqm =maxf;}
    public void setMinSqm(int minf){this.minSqm =minf;}
    public void setBedrooms(int rooms){this.bedrooms=rooms;}
    public void setBathrooms(int rooms){this.bathrooms=rooms;}
    public void setFloor(int f){this.floor=f;}
    public void setHeat(boolean heat){this.heat=heat;}

}
