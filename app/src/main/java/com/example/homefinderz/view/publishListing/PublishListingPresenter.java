package com.example.homefinderz.view.publishListing;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.model.Listing;
import com.example.homefinderz.view.Listing.ListingView;
import com.example.homefinderz.view.dao.AccountDAO;


public class PublishListingPresenter {

    private ListingView view;
    private AccountDAO accountDAO;
    private int accountID;

    /**
     * Initializes the PublishListingPresenter.
     * @param view An instance of the view
     */
    public PublishListingPresenter(ListingView view) {
        this.view = view;
        this.accountDAO = new AccountDAO();
    }

    /**
     * This method creates a new Listing
     */
    public void createListing() {
        int price        = view.getPrice();
        int squareMeters = view.getSQM();
        int bedrooms     = view.getBedrooms();
        int bathrooms    = view.getBathrooms();
        int floor        = view.getFloor();

        boolean heating  = view.getHeating();

        String location    = view.getLocation();
        String description = view.getDescription();
        Listing lis = Listing.createListing(accountDAO.createListingID(), price, location, squareMeters, bedrooms, bathrooms,
                                        floor, heating, description);
        if (lis != null) {
            Account account = accountDAO.findAccount(accountID); //find the account that is currently logged in
            account.addPublishedListing(lis);                    //add the listing to the account
            accountDAO.increaseListingsCount();
            accountDAO.update(account);                          //notify the database of the change to the account
            view.showSuccessMessage();
        }
        else { //this means that one or all of the values the user inputted were not valid or were missing
            view.showErrorMessage("Error!", "Please fill in the fields with valid values!");
        }
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
