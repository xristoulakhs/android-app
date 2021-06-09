package com.example.homefinderz.viewtests.publishlistingtests;

import com.example.homefinderz.view.Listing.ListingView;

public class PublishListingsViewStub implements ListingView {
    private int price;
    private int squareMeters;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private boolean heating;
    private String location;
    private String description;

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    @Override
    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    @Override
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    @Override
    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getSQM() {
        return squareMeters;
    }

    @Override
    public int getBedrooms() {
        return bedrooms;
    }

    @Override
    public int getBathrooms() {
        return bathrooms;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public boolean getHeating() {
        return heating;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        //stub
    }

    @Override
    public void showSuccessMessage() {
        //stub
    }
}
