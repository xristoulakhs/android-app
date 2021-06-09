package com.example.homefinderz.view.search;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.model.ArchivedSearch;
import com.example.homefinderz.model.Listing;
import com.example.homefinderz.model.Search;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchPresenter {

    private SearchView view;
    private AccountDAO accountDAO;

    private Account userAccount;
    private static int accountID;

    private Iterator<Listing> resultIt;

    private Search lastSearch;

    /**
     * Initialises the SearchPresenter.
     * @param view An instance of the view
     */
    public SearchPresenter(SearchView view) {
        this.view = view;
        accountDAO = new AccountDAO();
    }

    /**
     * This methods executes a search according to filters in the Search Page.
     * @return A list of Listings that fit the user's filter value's.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void doSearch() {
        int minPrice = ((view.getMinPrice()  == -1) ? Integer.MIN_VALUE : view.getMinPrice());
        int maxPrice = ((view.getMaxPrice()  == -1) ? Integer.MAX_VALUE : view.getMaxPrice());
        int minSqm   = ((view.getMinSQM()    == -1) ? Integer.MIN_VALUE : view.getMinSQM());
        int maxSqm   = ((view.getMaxSQM()    == -1) ? Integer.MAX_VALUE : view.getMaxSQM());
        int floor    = ((view.getFloor()     == -1) ? Integer.MIN_VALUE : view.getFloor());
        int beds     = ((view.getBedrooms()  == -1) ? Integer.MIN_VALUE : view.getBedrooms());
        int baths    = ((view.getBathrooms() == -1) ? Integer.MIN_VALUE : view.getBathrooms());

        boolean heating = view.getHeating();
        String location = view.getLocation();

        int sID = accountDAO.createSearchID();

        lastSearch = new Search(sID,minPrice,maxPrice,location,maxSqm,minSqm,beds,baths,floor,heating);

        List<Listing> results = new ArrayList<Listing>();

        Iterator<Account> accIt = accountDAO.getPremAccounts().iterator();
        while (accIt.hasNext()) {
            Account acc = accIt.next();
            if(!(acc.getId() == accountID)) {
                List<Listing> usrListings = acc.getPublishedListings();
                results.addAll(findResultList(lastSearch, usrListings));
            }
        }
        accIt = accountDAO.getFreeAccounts().iterator();
        while (accIt.hasNext()) {
            Account acc = accIt.next();
            if(!(acc.getId() == accountID)) {
                List<Listing> usrListings = acc.getPublishedListings();
                results.addAll(findResultList(lastSearch, usrListings));
            }
        }
        accountDAO.findAccount(accountID).addArchivedSearch(new ArchivedSearch(new Search(lastSearch.getSearchId(),
                lastSearch.getMinPrice(), lastSearch.getMaxPrice(), lastSearch.getLocation(),
                lastSearch.getMaxSqm(),lastSearch.getMinSqm(), lastSearch.getBedrooms(),
                lastSearch.getBathrooms(), lastSearch.getFloor(), lastSearch.getHeat())));

        resultIt = results.iterator();
        view.showResults();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void doSearch(Search newSearch) {
        List<Listing> results = new ArrayList<Listing>();

        Iterator<Account> accIt = accountDAO.getPremAccounts().iterator();
        while (accIt.hasNext()) {
            Account acc = accIt.next();
            if(!(acc.getId() == accountID)) {
                List<Listing> usrListings = acc.getPublishedListings();
                results.addAll(findResultList(newSearch, usrListings));
            }
        }
        accIt = accountDAO.getFreeAccounts().iterator();
        while (accIt.hasNext()) {
            Account acc = accIt.next();
            if(!(acc.getId() == accountID)) {
                List<Listing> usrListings = acc.getPublishedListings();
                results.addAll(findResultList(newSearch, usrListings));
            }
        }
        accountDAO.findAccount(accountID).addArchivedSearch(new ArchivedSearch(new Search(newSearch.getSearchId(),
                newSearch.getMinPrice(), newSearch.getMaxPrice(), newSearch.getLocation(),
                newSearch.getMaxSqm(),newSearch.getMinSqm(), newSearch.getBedrooms(),
                newSearch.getBathrooms(), newSearch.getFloor(), newSearch.getHeat())));

        resultIt = results.iterator();
        view.showResults();
    }

    public boolean hasNextResult() {
        return resultIt.hasNext();
    }
    public Listing getNextResult() {
        return resultIt.next();
    }

    /**
     * Takes a user's List of Listings and compares it with the Search the passed to it
     *
     * @param newSearch a Search object that holds the filter values defined by the user
     * @param accountListings all listings that an account has published
     * @return a sublist of the accountListings fitting the search filters
     */
    public List<Listing> findResultList(Search newSearch, List<Listing> accountListings){
        List<Listing> resultList = new ArrayList<>();
        if (!accountListings.isEmpty()) {
            for (Listing listing : accountListings) {
                if (listing.getPrice() <= newSearch.getMaxPrice() &&
                    listing.getPrice() >= newSearch.getMinPrice() &&
                    listing.getLocation().equals(newSearch.getLocation()) &&
                    listing.getSqm() <= newSearch.getMaxSqm() &&
                    listing.getSqm() >= newSearch.getMinSqm() &&
                    listing.getBedrooms() == newSearch.getBedrooms() &&
                    listing.getBathrooms() == newSearch.getBathrooms() &&
                    listing.getFloor() == newSearch.getFloor()) {

                    if (newSearch.getHeat()) {
                        if (listing.getHeating()) {
                            resultList.add(listing);
                        }
                    } else {
                        resultList.add(listing);
                    }
                }
            }
        }
        return new ArrayList<Listing>(resultList);
    }

    /**
     * This method is called when the user accepts a listing by pressing the button "Accept"
     * @param lis the Listing which the user accepted
     */
    public void addAcceptedListing(Listing lis) {
        accountDAO.findAccount(accountID).approveListing(lis);
    }

    /**
     * This method specifies the account ID that belongs to current user's account
     * @param accountID the current user's account ID
     */
    public void setAccount(int accountID) {
        this.accountID = accountID;
    }

    /**
     * This method retrieves the current's account ID
     * @return the current user's account ID
     */
    public int getAccountLoggedIn() {
        return accountID;
    }

    /**
     * This method informs if the current account is deleted or not
     * @return true if the current account cannot be found in database,
     *         false if the current account exists in database
     */
    public boolean accountDeleted() {
        return accountDAO.findAccount(accountID) == null;
    }

    public void saveSearch() {
        if (lastSearch == null) {
            view.showErrorMessage();
        }
        else {
            accountDAO.findAccount(accountID).storeSearch(new Search(lastSearch.getSearchId(),
                    lastSearch.getMinPrice(), lastSearch.getMaxPrice(), lastSearch.getLocation(),
                    lastSearch.getMaxSqm(),lastSearch.getMinSqm(), lastSearch.getBedrooms(),
                    lastSearch.getBathrooms(), lastSearch.getFloor(), lastSearch.getHeat()));
            lastSearch = null;
            view.showSavedMessage();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void searchSaved(int searchID) {
        if (searchID == -1) {
            view.showErrorMessage();
        }
        else {
            doSearch(accountDAO.findSearch(accountID, searchID));
        }
    }
}
