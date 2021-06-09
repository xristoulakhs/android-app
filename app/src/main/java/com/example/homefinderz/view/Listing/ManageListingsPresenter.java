package com.example.homefinderz.view.Listing;

import com.example.homefinderz.model.Listing;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.List;

public class ManageListingsPresenter {

    private ManageListingsView view;
    private AccountDAO accountDAO;
    private int accountID;

    /**
     * Initializes the ManageListingsPresenter.
     * @param view An instance view
     */
    public ManageListingsPresenter(ManageListingsView view) {
        this.view = view;
        this.accountDAO = new AccountDAO();
    }

    public List<Listing> getListings() {
        return accountDAO.findAccount(accountID).getPublishedListings();
    }

    /**
     * This method deletes a Listing from the users published listings
     * @return lis the Listing to be deleted
     */
    public void deleteListing(int lis) {
        accountDAO.removeListing(accountID, lis);
    }

    /**
     * This method declares an account as the current logged in account
     * @param accountID the account that logged in
     */
    public void setAccountLoggedIn(int accountID) {
        this.accountID = accountID;
    }

    /**
     * This method retrieves the ID of the account currently logged in
     * @return the current account's account ID
     */
    public int getAccountID() {
        return accountID;
    }
}
