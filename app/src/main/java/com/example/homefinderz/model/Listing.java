package com.example.homefinderz.model;

public class Listing {

    private int id;
    private int price;
    private String location;
    private int squareMeters;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private boolean heating;
    private String description;

    private String image;

    public Listing(int id, int price, String location, int squareMeters, int bedrooms, int bathrooms, int floor, boolean heating, String description) {
        this.id = id;
        this.price = price;
        this.location = location;
        this.squareMeters = squareMeters;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floor = floor;
        this.heating = heating;
        this.description = description;
    }

    /**
     * This method is about checking that the listing
     * which is about to be created has right values. After it pass the chech at the if statement
     * it creates a Listing-type variable and return it or null else.
     * @param id
     * @param price
     * @param location
     * @param squareMeters
     * @param bedrooms
     * @param bathrooms
     * @param floor
     * @param heating
     * @param description
     * @return
     */
    public static Listing createListing(int id, int price, String location, int squareMeters, int bedrooms, int bathrooms, int floor, boolean heating, String description){

            if(price>0 && squareMeters>0 && bedrooms>0 && bathrooms>0){   //also check if floor is number?
                return new Listing(id, price, location, squareMeters, bedrooms, bathrooms, floor, heating, description);
            }
            return null;
    }


    public int getId() { return id; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int new_price) {
        if(new_price>0) {
            this.price = new_price;
        }
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getSqm() {
        return squareMeters;
    }
    public void setSqm(int new_squareMeters) {
        if(new_squareMeters>0) {
            this.squareMeters = new_squareMeters;
        }
    }

    public int getBedrooms() {
        return bedrooms;
    }
    public void setBedrooms(int new_bedrooms) {
        if(new_bedrooms>0){
            this.bedrooms = new_bedrooms;
        }
    }

    public int getBathrooms() {
        return bathrooms;
    }
    public void setBathrooms(int new_bathrooms) {
        if(new_bathrooms>0){
            this.bathrooms = new_bathrooms;
        }
    }

    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean getHeating() {
        return heating;
    }
    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
