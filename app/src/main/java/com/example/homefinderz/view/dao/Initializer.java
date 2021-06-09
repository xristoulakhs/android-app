package com.example.homefinderz.view.dao;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.model.Listing;

public abstract class Initializer {

    protected abstract void eraseData();

    public abstract AccountDAO getDAO();

    public void prepareData() {
        eraseData();

        AccountDAO accountDAO = getDAO();


        //Account creation. Creates 8 accounts
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Jason", "Kalogerakis",
                "jkalo@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Chris", "Manoudakis",
                "chrismano@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "George", "Vellis",
                "gevelli@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Amelia", "Watson",
                "bestdetective@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Gawr", "Gura",
                "dwarfshark@gmail.com", "p"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Mark", "Grayson",
                "invincible@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Peter", "Parker",
                "notaspider@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Jim", "Gordon",
                "bestcommissioner@gmail.com", "password"));
        accountDAO.add(Account.createAccount(accountDAO.createAccountID(),
                "Elena", "Mina",
                "elena@gmail.com", "123"));

        //Upgrading Accounts. Upgrades 3 accounts
        accountDAO.upgrade(accountDAO.findAccount("dwarfshark@gmail.com"));
        accountDAO.upgrade(accountDAO.findAccount("notaspider@gmail.com"));
        accountDAO.upgrade(accountDAO.findAccount("jkalo@gmail.com"));


        //Publishing Listings.
        Account publisher = accountDAO.findAccount("dwarfshark@gmail.com");
        publisher.addPublishedListing(Listing.createListing(accountDAO.createListingID(), 300, "Athens", 60, 1,1,1,false, "add description"));
        publisher.addPublishedListing(Listing.createListing(accountDAO.createListingID(), 300, "Athens", 60, 1,1,1,false, "add description"));
        accountDAO.update(publisher);

        //Publish Listing from a different account.
        publisher = accountDAO.findAccount("chrismano@gmail.com");
        publisher.addPublishedListing(Listing.createListing(accountDAO.createListingID(), 300, "Athens", 60, 1,1,1,false, "add description"));
        accountDAO.update(publisher);

        //Publish Listing from yet another different account.
        publisher = accountDAO.findAccount("gevelli@gmail.com");
        publisher.addPublishedListing(Listing.createListing(accountDAO.createListingID(), 300, "Athens", 60, 1,1,1,false, "add description"));
        publisher.addPublishedListing(Listing.createListing(accountDAO.createListingID(), 300, "Athens", 60, 1,1,1,false, "add description"));
        accountDAO.update(publisher);
    }
}
