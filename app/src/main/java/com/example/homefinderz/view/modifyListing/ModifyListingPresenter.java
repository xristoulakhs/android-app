package com.example.homefinderz.view.modifyListing;

import com.example.homefinderz.view.Listing.ListingView;
import com.example.homefinderz.view.dao.AccountDAO;


public class ModifyListingPresenter {

    private ListingView view;
    private AccountDAO accountDAO;
    private int listingID;
    private int accountID;

    public ModifyListingPresenter(ListingView view) {
        this.view = view;
        this.accountDAO = new AccountDAO();
    }

    public int getPrice () {
        return accountDAO.findListing(accountID, listingID).getPrice();
    }
    public int getSQM () {
        return accountDAO.findListing(accountID, listingID).getSqm();
    }
    public int getBeds () {
        return accountDAO.findListing(accountID, listingID).getBedrooms();
    }
    public int getBaths () {
        return accountDAO.findListing(accountID, listingID).getBathrooms();
    }
    public int getFloor () {
        return accountDAO.findListing(accountID, listingID).getFloor();
    }

    public String getLocation () {
        return accountDAO.findListing(accountID, listingID).getLocation();
    }
    public String getDescription () {
        return accountDAO.findListing(accountID, listingID).getDescription();
    }

    public boolean getHeat() {
        return accountDAO.findListing(accountID, listingID).getHeating();
    }

    public void setListingID(int listingID) {
        this.listingID = listingID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    /**
     * This method modifies a Listing's attributes
     */
    public void saveChanges() {
        int price        = view.getPrice();
        int squareMeters = view.getSQM();
        int bedrooms     = view.getBedrooms();
        int bathrooms    = view.getBathrooms();
        int floor        = view.getFloor();

        boolean heating  = view.getHeating();

        String location    = view.getLocation();
        String description = view.getDescription();

        boolean modifySuccess = true;

        if(price != ModifyListingActivity.EMPTY_TEXT_FIELD) {
            if (price <= 0) {
                modifySuccess = false;
            }
        }
        if(squareMeters != ModifyListingActivity.EMPTY_TEXT_FIELD) {
            if (squareMeters <= 0) {
                modifySuccess = false;
            }
        }
        if(bedrooms != ModifyListingActivity.EMPTY_TEXT_FIELD) {
            if (bedrooms <= 0) {
                modifySuccess = false;
            }
        }
        if(bathrooms != ModifyListingActivity.EMPTY_TEXT_FIELD) {
            if (bathrooms <= 0) {
                modifySuccess = false;
            }
        }
        if(modifySuccess) {
            if(price != ModifyListingActivity.EMPTY_TEXT_FIELD) {
                accountDAO.findListing(accountID, listingID).setPrice(price);
            }
            if(squareMeters != ModifyListingActivity.EMPTY_TEXT_FIELD) {
                accountDAO.findListing(accountID, listingID).setSqm(squareMeters);
            }
            if(bedrooms != ModifyListingActivity.EMPTY_TEXT_FIELD) {
                accountDAO.findListing(accountID, listingID).setBedrooms(bedrooms);
            }
            if(bathrooms != ModifyListingActivity.EMPTY_TEXT_FIELD) {
                accountDAO.findListing(accountID, listingID).setBathrooms(bathrooms);
            }
            if(floor != ModifyListingActivity.EMPTY_TEXT_FIELD) {
                accountDAO.findListing(accountID, listingID).setFloor(floor);
            }
            if(heating != accountDAO.findListing(accountID, listingID).getHeating()) {
                accountDAO.findListing(accountID, listingID).setHeating(heating);
            }
            if(!location.equals("")) {
                accountDAO.findListing(accountID, listingID).setLocation(location);
            }
            if(!description.equals("")) {
                accountDAO.findListing(accountID, listingID).setDescription(description);
            }
            //accountDAO.update(accountDAO.findAccount(accountID), accountDAO.findListing(accountID, listingID));
            view.showSuccessMessage();
        }
        else
            view.showErrorMessage("Error!", "Invalid data!");

    }
}
