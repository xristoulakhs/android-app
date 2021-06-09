package com.example.homefinderz.view.search;

public interface SearchView {

    void onVisitProfile();

    void setMinPrice(int price);
    void setMaxPrice(int price);
    void setMinSQM(int sqm);
    void setMaxSQM(int sqm);
    void setBedrooms(int beds);
    void setBathrooms(int baths);
    void setFloor(int floor);

    void setHeating(boolean heat);

    void setLocation(String location);

    int getMinPrice();
    int getMaxPrice();
    int getMinSQM();
    int getMaxSQM();
    int getBedrooms();
    int getBathrooms();
    int getFloor();

    boolean getHeating();

    String getLocation();

    void showResults();

    void showSavedMessage();

    void showErrorMessage();
}
