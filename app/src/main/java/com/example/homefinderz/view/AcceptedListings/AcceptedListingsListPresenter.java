package com.example.homefinderz.view.AcceptedListings;

import com.example.homefinderz.model.Listing;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.List;

public class AcceptedListingsListPresenter {

    private AcceptedListingsListView view;
    private int accountID;
    private AccountDAO accountDAO;

    public AcceptedListingsListPresenter(AcceptedListingsListView view) {
        this.view = view;
        accountDAO = new AccountDAO();
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getAccountID() {
        return accountID;
    }

    public List<Listing> getAcceptedListings() {
        return accountDAO.findAccount(accountID).getApprovedListings();
    }

    public void deleteApprovedListing(int listingID) {
        accountDAO.removeApproval(accountID, listingID);
    }
}
