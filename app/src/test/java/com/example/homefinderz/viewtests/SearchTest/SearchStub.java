package com.example.homefinderz.viewtests.SearchTest;

import com.example.homefinderz.view.search.SearchView;

public class SearchStub implements SearchView {

    private int minPrice;
    private int maxPrice;
    private String location;
    private int maxSQM;
    private int minSQM;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private boolean heating;

    @Override
    public void onVisitProfile() {
        //stub normally starts the AccountUI Activity
    }

    @Override
    public void setMinPrice(int price) {
        this.minPrice = price;
    }

    @Override
    public void setMaxPrice(int price) {
        this.maxPrice = price;
    }

    @Override
    public void setMinSQM(int sqm) {
        this.minSQM = sqm;
    }

    @Override
    public void setMaxSQM(int sqm) {
        this.maxSQM = sqm;
    }

    @Override
    public void setBedrooms(int beds) {
        this.bedrooms = beds;
    }

    @Override
    public void setBathrooms(int baths) {
        this.bathrooms = baths;
    }

    @Override
    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public void setHeating(boolean heat) {
        this.heating = heat;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int getMinPrice() {
        return minPrice;
    }

    @Override
    public int getMaxPrice() {
        return maxPrice;
    }

    @Override
    public int getMinSQM() {
        return minSQM;
    }

    @Override
    public int getMaxSQM() {
        return maxSQM;
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
        return "Athens";
    }

    @Override
    public void showResults() {
        //stub
    }

    @Override
    public void showSavedMessage() {
        //stub
    }

    @Override
    public void showErrorMessage() {
        //stub
    }
}
