package com.example.homefinderz.model;

import java.util.ArrayList;
import java.util.List;


public class Account {
    private int id;
    private String userName;
    private String userSurname;
    private String email;
    private String password;

    private boolean premium = false;

    private List<Listing> approvedListings; //publisherID, listingID
    private List<Listing> publishedListings; //publisherID, listing

    private List<Search> storedSearches;

    private List<ArchivedSearch> archivedSearches;

    public Account(int id, String name, String surname, String email, String pass) {
        this.id = id;
        this.userName = name;
        this.userSurname = surname;
        this.email = email;
        this.password = pass;
        this.approvedListings  = new ArrayList<>();
        this.publishedListings = new ArrayList<>();
        this.storedSearches    = new ArrayList<>(4);        //giati 4?
        this.archivedSearches = new ArrayList<>();
    }

    /**
     * method used in order to check that the account which we want to create
     * has right values.
     *
     * @param id the id of the account to be created
     * @param name the name of the user that wants to create the account
     * @param surname the surname of the user that wants to create the account
     * @param email the email of the user that wants to create the account
     * @param pass the password of the account to be created
     * @return null if the values aren't valid. The new account if the values are valid
     */
    public static Account createAccount(int id, String name, String surname, String email, String pass) {
        if (!email.equals("") && !name.equals("") && !surname.equals("") && !pass.equals("")) {
            //System.out.println(id);
            return new Account(id, name, surname, email, pass);
        }
        return null;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserSurname() { return userSurname; }
    public void setUserSurname(String userSurname) { this.userSurname = userSurname; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isPremium() { return premium; }
    public void makePremium()  { this.premium = true; }

    public List<Listing> getApprovedListings() { return approvedListings; } // we actually want to be able to remove one Listing
    public void approveListing(Listing listing)   { this.approvedListings.add(listing); }

    public List<Listing> getPublishedListings()   { return publishedListings; }
    public void addPublishedListing(Listing listing) { this.publishedListings.add(listing); }

    public List<Search> getStoredSearches() { return storedSearches; }
    public void storeSearch(Search search) { this.storedSearches.add(search); }

    public List<ArchivedSearch> getArchivedSearches() {
        return new ArrayList<>(archivedSearches);
    }

    public void addArchivedSearch(ArchivedSearch arc) {
        this.archivedSearches.add(arc);
    }
}
