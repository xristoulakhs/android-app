package com.example.homefinderz.viewtests.SearchTest;

import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.search.SearchPresenter;
import com.example.homefinderz.view.search.SearchView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchPresenterTest {

    private static SearchPresenter presenter;
    private static SearchView view;

    @BeforeClass
    public static void setUp() {
        new MemoryInitializer().prepareData();
        view = new SearchStub();
    }

    /**
     * we want to reset the presenter before each test because some tests require the
     * presenter's data to be unaffected by previous tests
     */
    @Before
    public void resetPresenter() {
        presenter = new SearchPresenter(view);
        presenter.setAccount(new AccountDAO().findAccount("jkalo@gmail.com").getId());
    }

    @Test
    public void executeValidSearch() {
        fillSearchFields();

        int counterBefore = AccountDAO.getSearchID();
        presenter.doSearch();
        Assert.assertEquals(counterBefore + 1, AccountDAO.getSearchID());
        Assert.assertTrue(presenter.hasNextResult());
    }

    /**
     * Checks that an empty search can be executed
     */
    @Test
    public void executeEmptySearch() {
        emptySearchFields();

        int counterBefore = AccountDAO.getSearchID();

        //Even with empty fields, the search should be created and executed
        presenter.doSearch();
        Assert.assertEquals(counterBefore + 1, AccountDAO.getSearchID());
    }

    /**
     * Checks that an empty search does not return results
     */
    @Test
    public void executeEmptySearch_noResults() {
        emptySearchFields();

        //Even with empty fields, the search should be created and executed
        presenter.doSearch();

        //However, with all the search fields empty no results will be found
        Assert.assertFalse(presenter.hasNextResult());
    }

    /**
     * Checks that a not empty search returns results
     */
    @Test
    public void executeValidSearch_notAllFields(){
        emptySearchFields();
        view.setFloor(1);
        view.setBedrooms(1);
        view.setBathrooms(1);
        presenter.doSearch();
        Assert.assertTrue(presenter.hasNextResult());
    }

    /**
     * Checks that a search can not be saved if a search has not been executed
     */
    @Test
    public void saveSearch_noExecutedSearch() {
        fillSearchFields();
        int accountID = new AccountDAO().findAccount("jkalo@gmail.com").getId();

        int savedSearchesSize = new AccountDAO().findAccount(accountID).getStoredSearches().size();
        //Trying to save a search without actually executing one should do nothing
        presenter.saveSearch();
        Assert.assertEquals(savedSearchesSize, new AccountDAO().findAccount(accountID).getStoredSearches().size());
    }

    /**
     * Checks that a search can be saved only if a search has not been executed beforehand
     */
    @Test
    public void saveSearch_withExecutedSearch(){
        fillSearchFields();
        int accountID = new AccountDAO().findAccount("jkalo@gmail.com").getId();
        int savedSearchesSize = new AccountDAO().findAccount(accountID).getStoredSearches().size();

        presenter.doSearch();
        presenter.saveSearch();
        Assert.assertEquals(savedSearchesSize + 1, new AccountDAO().findAccount(accountID).getStoredSearches().size());
    }

    /**
     * Tests if the same search can be saved twice
     */
    @Test
    public void saveSearchTwice(){
        fillSearchFields();
        int accountID = new AccountDAO().findAccount("jkalo@gmail.com").getId();
        int savedSearchesSize = new AccountDAO().findAccount(accountID).getStoredSearches().size();
        presenter.doSearch();
        presenter.saveSearch();
        savedSearchesSize++;    //number of saved searches after first "save"
        presenter.saveSearch();
        Assert.assertEquals(savedSearchesSize, new AccountDAO().findAccount(accountID).getStoredSearches().size());
    }

    /**
     * Checks that the execution of an already saved search is not handled as a new search
     */
    @Test
    public void executeSavedSearch(){
        fillSearchFields();
        int accountID = new AccountDAO().findAccount("jkalo@gmail.com").getId();
        presenter.doSearch();
        presenter.saveSearch();

        int counterBeforeSavedSearch = AccountDAO.getSearchID();
        int savedSearchID = new AccountDAO().findAccount(accountID).getStoredSearches().get(0).getSearchId();
        presenter.searchSaved(savedSearchID);                                    //since we do searchID++ on search creation
        Assert.assertEquals(counterBeforeSavedSearch, AccountDAO.getSearchID()); //searchID shouldn't be incremented
        Assert.assertTrue(presenter.hasNextResult());
    }

    /**
     * Resembles a search with valid fields
     */
    private void fillSearchFields() {
        view.setMinPrice(1);
        view.setMaxPrice(10000);
        view.setMinSQM(1);
        view.setMaxSQM(10000);
        view.setBedrooms(1);
        view.setBathrooms(1);
        view.setFloor(1);
        view.setHeating(false);
        view.setLocation("Athens");
    }

    /**
     * when the user hasn't filled in some field that returns int
     * the view returns -1 and the presenter then does the appropriate checks
     */

    private void emptySearchFields() {
        view.setMinPrice(-1);
        view.setMaxPrice(-1);
        view.setMinSQM(-1);
        view.setMaxSQM(-1);
        view.setBedrooms(-1);
        view.setBathrooms(-1);
        view.setFloor(-1);
        view.setHeating(false);     //Is supposed to be a checkbox so even unchecked it is false not empty
        view.setLocation("Athens"); //Is supposed to get a string from a dropdown so it wil never be empty
    }

}
