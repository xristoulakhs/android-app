package com.example.homefinderz.viewtests.SearchHistoryTest;

import com.example.homefinderz.view.SearchHistory.SearchHistoryPresenter;
import com.example.homefinderz.view.SearchHistory.SearchHistoryView;
import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.search.SearchPresenter;
import com.example.homefinderz.view.search.SearchView;
import com.example.homefinderz.viewtests.SearchTest.SearchStub;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SearchHistoryPresenterTest {
    private static SearchHistoryPresenter presenter;
    private static SearchHistoryView view;
    private static SearchPresenter search_presenter;
    private static SearchView search_view;

    @BeforeClass
    public static void setUp() {
        new MemoryInitializer().prepareData();
        view = new SearchHistoryStub();
        search_view = new SearchStub();
    }

    /**
     * we want to reset the presenter before each test because some tests require the
     * presenter's data to be unaffected by previous tests
     */
    @Before
    public void resetPresenter() {
        presenter = new SearchHistoryPresenter(view);
        search_presenter = new SearchPresenter(search_view);
        presenter.setAccountID(new AccountDAO().findAccount("jkalo@gmail.com").getId());
        search_presenter.setAccount(new AccountDAO().findAccount("jkalo@gmail.com").getId());
    }
    /**
     * Tests that an executed search is saved in search history
     */
    @Test
    public void addSearchToHistory(){
        int historySize = presenter.getSearchHistory().size();

        fillSearchFields();
        search_presenter.doSearch();

        Assert.assertEquals(historySize+1, presenter.getSearchHistory().size());
    }

    /**
     * Resembles a search with valid fields
     */
    private void fillSearchFields() {
        search_view.setMinPrice(1);
        search_view.setMaxPrice(10000);
        search_view.setMinSQM(1);
        search_view.setMaxSQM(10000);
        search_view.setBedrooms(1);
        search_view.setBathrooms(1);
        search_view.setFloor(1);
        search_view.setHeating(false);
        search_view.setLocation("Athens");
    }
}
