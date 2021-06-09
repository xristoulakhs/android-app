package com.example.homefinderz.view.dao;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.model.Listing;
import com.example.homefinderz.model.Search;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    /**
     * Having a different ArrayList for the different kinds of Accounts
     * will help during Search.
     * You can first search for Listings by premium Users by iterating through
     * the List premAccounts
     * and then similarly for Listings by free Users with the List freeAccounts.
     */
    private static List<Account> freeAccounts = new ArrayList<>();;    //holding all the free accounts
    private static List<Account> premAccounts = new ArrayList<>();;    //holding all the premium accounts

    private static int numOfAccounts = 0;
    private static int numOfListings = 0;
    private static int searchID      = 0;
    private static int accountID     = 0;
    private static int listingID     = 0;

    public AccountDAO() {}

    public int getNumOfAccounts() { return numOfAccounts; }

    public int getNumOfListings() { return numOfListings; }

    public void increaseListingsCount() { numOfListings++; }

    /**
     * For testing purposes.
     * We will check if it is increased.
     * That would mean a search was created.
     */
    public static int getSearchID() {
        return searchID;
    }

    public List<Account> getFreeAccounts() {
        List<Account> result = new ArrayList<>();
        result.addAll(freeAccounts);
        return result;
    }
    public List<Account> getPremAccounts() {
        List<Account> result = new ArrayList<>();
        result.addAll(premAccounts);
        return result;
    }

    /**
     * This is called to store a newly made account to the Database
     * @param acc the new account that was created
     */
    public void add(Account acc) {
        freeAccounts.add(acc);
        numOfAccounts++;

    }

    /**
     * This is called when a free account is turned to a premium account
     * First, we remove the old account from the list of the free accounts
     * Then, we call the method makePremium and we add it to the list of premium accounts
     * @param acc the account to be upgraded
     */
    public void upgrade(Account acc) {
        freeAccounts.remove(acc);
        acc.makePremium();
        premAccounts.add(acc);
    }

    /**
     * This handles the account deletion.
     * It checks if the account to be deleted is Premium and it removes it from its respective list.
     * @param acc the account to be deleted
     */
    public void removeAccount(Account acc) {
        if (acc.isPremium()) {
            premAccounts.remove(acc);
        }
        else {
            freeAccounts.remove(acc);
        }
        numOfAccounts--;
    }

    /**
     * This notifies our database of the changes made to an account
     * It checks if the account that has been changed is premium then it iterates
     * over the respective list.
     *
     * When it finds the index of the account's position in the list, it replaces the
     * old version of the account with the new one.
     *
     * @param acc the changed version of the account.
     */
    public void update(Account acc) {
        if (acc.isPremium()) {
            for (int i = 0; i < premAccounts.size(); i++) {
                if (premAccounts.get(i).getId() == acc.getId()) {
                    premAccounts.set(i, acc);
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < freeAccounts.size(); i++) {
                if (freeAccounts.get(i).getId() == acc.getId()) {
                    freeAccounts.set(i, acc);
                    break;
                }
            }
        }
    }

    public Account findAccount(String email) {
        for (Account acc : premAccounts) {
            if (acc.getEmail().equals(email)) {
                return acc;
            }
        }
        for (Account acc : freeAccounts) {
            if (acc.getEmail().equals(email)) {
                return acc;
            }
        }
        return null;
    }
    public Account findAccount(int ID) {
        for (Account acc : premAccounts) {
            if (acc.getId() == ID) {
                return acc;
            }
        }

        for (Account acc : freeAccounts) {
            if (acc.getId() == ID) {
                return acc;
            }
        }
        return null;
    }

    public int createSearchID() {
        return searchID++;
    }
    public int createAccountID() {
        return accountID++;
    }
    public int createListingID() {
        return listingID++;
    }

    /**
     * Find a particular Listing
     * @param accountID the id of the account that is currently logged in
     * @param listingID the id of the listing the user selected to modify
     * @return the Listing that the user wants to modify
     */
    public Listing findListing(int accountID, int listingID) {
        for (Listing lis: findAccount(accountID).getPublishedListings()) {
            if(lis.getId() == listingID) {
                return lis;
            }
        }
        return null;
    }

    public Listing findListing(int listingID) {
        for (Account account: premAccounts) {
            for(Listing lis: account.getPublishedListings()) {
                if(lis.getId() == listingID) {
                    return lis;
                }
            }
        }
        for (Account account: freeAccounts) {
            for(Listing lis: account.getPublishedListings()) {
                if(lis.getId() == listingID) {
                    return lis;
                }
            }
        }
        return null;
    }

    public Search findSearch(int accountID, int searchID) {
        for (Search search: findAccount(accountID).getStoredSearches()) {
            if(search.getSearchId() == searchID) {
                return search;
            }
        }
        return null;
    }

    public void removeListing(int accountID, int lis) {
        Account acc = findAccount(accountID);
        List<Listing> listings = acc.getPublishedListings();
        for(int i = 0; i < listings.size(); i++) {
            if(listings.get(i).getId() == lis) {
                listings.remove(i);
                numOfListings--;
                break;
            }
        }
        update(acc);
    }

    public void removeApproval(int accountID, int listingID) {
        Account acc = findAccount(accountID);
        List<Listing> listings = acc.getApprovedListings();
        for(int i = 0; i < listings.size(); i++) {
            if(listings.get(i).getId() == listingID) {
                listings.remove(i);
                break;
            }
        }
        update(acc);
    }

    public void removeSearch(int accountID, int searchId) {
        Account acc = findAccount(accountID);
        List<Search> storedSearches = acc.getStoredSearches();
        for(int i = 0; i < storedSearches.size(); i++) {
            if(storedSearches.get(i).getSearchId() == searchId) {
                storedSearches.remove(i);
                break;
            }
        }
        update(acc);
    }
}
