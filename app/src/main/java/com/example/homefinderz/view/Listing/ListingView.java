package com.example.homefinderz.view.Listing;

public interface ListingView {

    int getPrice();
    int getSQM();
    int getBedrooms();
    int getBathrooms();
    int getFloor();

    boolean getHeating();

    String getLocation();
    String getDescription();

    void showErrorMessage(String title, String message);
    void showSuccessMessage();

    void setPrice(int price);

    void setSquareMeters(int squareMeters);

    void setBedrooms(int bedrooms);

    void setBathrooms(int bathrooms);

    void setFloor(int floor);

    void setHeating(boolean heating);

    void setLocation(String location);
    void setDescription(String description);

}
