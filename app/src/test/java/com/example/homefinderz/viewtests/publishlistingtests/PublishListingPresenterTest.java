package com.example.homefinderz.viewtests.publishlistingtests;

import com.example.homefinderz.view.Listing.ListingView;
import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.publishListing.PublishListingPresenter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PublishListingPresenterTest {

    private static PublishListingPresenter presenter;
    private static ListingView view;

    @BeforeClass
    public static void setUp() {
        new MemoryInitializer().prepareData();
        view = new PublishListingsViewStub();
        presenter = new PublishListingPresenter(view);
        presenter.setAccountID(new AccountDAO().findAccount("jkalo@gmail.com").getId());
    }

    @Test
    public void publishSuccessfully() {
        int listingsAmountBefore = new AccountDAO().getNumOfListings();
        setValidInput();

        presenter.createListing();
        Assert.assertEquals(listingsAmountBefore + 1, new AccountDAO().getNumOfListings());
    }
    @Test
    public void publishInvalidBaths() {
        int listingsAmountBefore = new AccountDAO().getNumOfListings();
        setValidInput();

        view.setBathrooms(-1);
        presenter.createListing();
        Assert.assertEquals(listingsAmountBefore, new AccountDAO().getNumOfListings());
    }

    @Test
    public void publishInvalidBeds() {
        int listingsAmountBefore = new AccountDAO().getNumOfListings();
        setValidInput();

        view.setBedrooms(-1);
        presenter.createListing();
        Assert.assertEquals(listingsAmountBefore, new AccountDAO().getNumOfListings());
    }

    @Test
    public void publishInvalidSQM() {
        int listingsAmountBefore = new AccountDAO().getNumOfListings();
        setValidInput();

        view.setSquareMeters(-1);
        presenter.createListing();
        Assert.assertEquals(listingsAmountBefore, new AccountDAO().getNumOfListings());
    }

    @Test
    public void publishInvalidPrice() {
        int listingsAmountBefore = new AccountDAO().getNumOfListings();
        setValidInput();

        view.setPrice(-1);
        presenter.createListing();
        Assert.assertEquals(listingsAmountBefore, new AccountDAO().getNumOfListings());
    }

    private void setValidInput() {
        view.setBathrooms(2);
        view.setBedrooms(3);
        view.setDescription("This is a description.");
        view.setFloor(3);
        view.setPrice(650);
        view.setSquareMeters(500);
        view.setHeating(true);
        view.setLocation("Rhodes");
    }
    
}
