package com.example.homefinderz.view.ViewListing;

import com.example.homefinderz.view.dao.AccountDAO;

public class ViewListingPresenter {

    private ViewListingView view;
    private int accountID;
    private int listingID;
    private AccountDAO accountDAO;

    public ViewListingPresenter(ViewListingView view) {
        this.view = view;
        accountDAO = new AccountDAO();
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public void setListingID(int listingID) {
        this.listingID = listingID;
    }


    public String getPrice () {
        return String.valueOf(accountDAO.findListing(listingID).getPrice());
    }
    public String getSQM () {
        return String.valueOf(accountDAO.findListing(listingID).getSqm());
    }
    public String getBeds () {
        return String.valueOf(accountDAO.findListing(listingID).getBedrooms());
    }
    public String getBaths () {
        return String.valueOf(accountDAO.findListing(listingID).getBathrooms());
    }
    public String getFloor () {
        return String.valueOf(accountDAO.findListing(listingID).getFloor());
    }

    public String getLocation () {
        return accountDAO.findListing(listingID).getLocation();
    }
    public String getDescription () {
        return accountDAO.findListing(listingID).getDescription();
    }

    public String getHeat() {
        if (accountDAO.findListing(listingID).getHeating()) {
            return "Has Heating";
        }
        else {
            return "Has no Heating";
        }
    }

    public String getID() {
        return String.valueOf(accountDAO.findListing(listingID).getId());
    }
}
