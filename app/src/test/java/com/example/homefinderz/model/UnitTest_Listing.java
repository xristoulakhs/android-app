package com.example.homefinderz.model;

import junit.framework.TestCase;
import org.junit.Assert;

import org.junit.BeforeClass;

import static com.example.homefinderz.model.testHelper_PassOrFail.*;
import static org.junit.Assert.assertEquals;

public class UnitTest_Listing extends TestCase {
    Listing listing_wrong;
    Listing newListing;
    testHelper_PassOrFail[] creation_cases = {VALID, PRICE, SQM, BEDROOMS, BATHROOMS};

    @BeforeClass
    public void setUp(){
        listing_wrong = Listing.createListing(1, 0, "Athens", 60, 1,1,1,false, "add description");
        newListing = Listing.createListing(2, 300, "Athens", 60, 1,1,1,false, "add description");
    }

    public void testCreateListing_multiple() {
        int ID = 0;
        for (int i = 0; i < creation_cases.length; i++) {
            Listing listing = null;
            switch (creation_cases[i]) {
                case VALID:
                    listing = Listing.createListing(ID, 300, "Athens", 60, 1,1,1,false, "add description");
                    break;
                case PRICE:
                    listing = Listing.createListing(ID, 0, "Athens", 60, 1,1,1,false, "add description");
                    break;
                case SQM:
                    listing = Listing.createListing(ID, 300, "Athens", 0, 1,1,1,false, "add description");
                    break;
                case BEDROOMS:
                    listing = Listing.createListing(ID, 300, "Athens", 60, 0,1,1,false, "add description");
                    break;
                case BATHROOMS:
                    listing = Listing.createListing(ID, 300, "Athens", 60, 1,0,1,false, "add description");
                    break;
            }
            ID++;
        }
    }

    public void testGetId() {
        int actual_id = newListing.getId();
        Assert.assertEquals(2,actual_id);
    }

    public void testGetPrice() {
        int actual_price = newListing.getPrice();
        Assert.assertEquals(300, actual_price);
    }

    public void testSetPrice_valid() {
        newListing.setPrice(400);
        int new_price = newListing.getPrice();
        assertEquals(400, new_price);
    }

    public void testSetPrice_invalid_zero() {
        newListing.setPrice(0);
        int new_price = newListing.getPrice();
        assertEquals(300, new_price);
    }

    public void testGetLocation() {
        String actual_location = newListing.getLocation();
        Assert.assertEquals("Athens", actual_location);
    }

    public void testSetLocation() {
        newListing.setLocation("Kallithea");
        String new_location = newListing.getLocation();
        assertEquals("Kallithea", new_location);
    }

    public void testGetSquareFeet() {
        int actual_sqm = newListing.getSqm();
        Assert.assertEquals(60, actual_sqm);
    }


    public void testSetSquareFeet_valid() {
        newListing.setSqm(100);
        int new_sqm = newListing.getSqm();
        assertEquals(100, new_sqm);
    }

    public void testSetSquareFeet_invalid() {
        newListing.setSqm(0);
        int new_sqm = newListing.getSqm();
        assertEquals(60, new_sqm);
    }

    public void testGetBedrooms() {
        int actual_beds = newListing.getBedrooms();
        Assert.assertEquals(1, actual_beds);
    }

    public void testSetBedrooms_valid() {
        newListing.setBedrooms(2);
        int new_beds = newListing.getBedrooms();
        assertEquals(2, new_beds);
    }

    public void testSetBedrooms_invalid() {
        newListing.setBedrooms(0);
        int new_beds = newListing.getBedrooms();
        assertEquals(1, new_beds);
    }

    public void testGetBathrooms() {
        int actual_baths = newListing.getBathrooms();
        Assert.assertEquals(1, actual_baths);
    }

    public void testSetBathrooms_invalid() {
        newListing.setBathrooms(2);
        int new_baths = newListing.getBathrooms();
        assertEquals(2, new_baths);
    }

    public void testSetBathrooms_valid() {
        newListing.setBathrooms(0);
        int new_baths = newListing.getBathrooms();
        assertEquals(1, new_baths);
    }

    public void testGetFloor() {
        int actual_floor = newListing.getFloor();
        Assert.assertEquals(1,actual_floor);
    }

    public void testSetFloor_valid() {
        newListing.setFloor(2);
        int new_floor = newListing.getFloor();
        assertEquals(2, new_floor);
    }

    public void testIsHeating() {
        boolean actual_value= newListing.getHeating();
        Assert.assertFalse(actual_value);
    }

    public void testSetHeating() {
        newListing.setHeating(true);
        boolean new_heating = newListing.getHeating();
        assertEquals(true, new_heating);
    }

    public void testGetDescription() {
        String actual_text = newListing.getDescription();
        Assert.assertEquals("add description", actual_text);
    }

    public void testSetDescription() {
        newListing.setDescription("new apartment");
        String new_text = newListing.getDescription();
        assertEquals("new apartment", new_text);
    }
}