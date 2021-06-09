package com.example.homefinderz.viewtests.modifylistingtests;

import com.example.homefinderz.model.Listing;
import com.example.homefinderz.view.Listing.ListingView;
import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.modifyListing.ModifyListingActivity;
import com.example.homefinderz.view.modifyListing.ModifyListingPresenter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModifyListingPresenterTest {

    private static ModifyListingPresenter presenter;
    private static ListingView view;

    private static int id;
    private static int testPrice          = 560;
    private static String testLocation    = "Rhodes";
    private static int testSquareMeters   = 480;
    private static int testBedrooms       = 3;
    private static int testBathrooms      = 2;
    private static int testFloor          = 4;
    private static boolean testHeating    = true;
    private static String testDescription = "Description";

    @BeforeClass
    public static void setUp() {
        new MemoryInitializer().prepareData();
        view = new ModifyListingViewStub();
        presenter = new ModifyListingPresenter(view);
        presenter.setAccountID(new AccountDAO().findAccount("jkalo@gmail.com").getId());
        prepareTestListing();
    }
    public static void prepareTestListing() {
        Listing lis = Listing.createListing(new AccountDAO().createListingID(), testPrice, testLocation, testSquareMeters, testBedrooms, testBathrooms,
                testFloor, testHeating, testDescription);
        id = lis.getId();
        new AccountDAO().findAccount("jkalo@gmail.com").addPublishedListing(lis);
        presenter.setListingID(id);
    }

    @Test
    public void gettersTest() {
        prepareTestListing();

        Assert.assertEquals(testPrice, presenter.getPrice());

        Assert.assertEquals(testLocation, presenter.getLocation());

        Assert.assertEquals(testSquareMeters, presenter.getSQM());

        Assert.assertEquals(testBedrooms, presenter.getBeds());

        Assert.assertEquals(testBathrooms, presenter.getBaths());

        Assert.assertEquals(testFloor, presenter.getFloor());

        Assert.assertEquals(testHeating, presenter.getHeat());

        Assert.assertEquals(testDescription, presenter.getDescription());
    }

    @Test
    public void invalidInputTest() {
        prepareTestListing();

        setEmptyInput();
        view.setPrice(-1);
        view.setFloor(2);
        presenter.saveChanges();
        Assert.assertEquals(testFloor, presenter.getFloor());

        setEmptyInput();
        view.setBathrooms(-1);
        view.setDescription("a test");
        presenter.saveChanges();
        Assert.assertEquals(testDescription, presenter.getDescription());

        setEmptyInput();
        view.setBedrooms(-1);
        view.setLocation("Moon");
        presenter.saveChanges();
        Assert.assertEquals(testLocation, presenter.getLocation());

        setEmptyInput();
        view.setSquareMeters(-5);
        view.setFloor(1);
        presenter.saveChanges();
        Assert.assertEquals(testFloor, presenter.getFloor());
    }

    @Test
    public void validInputTest() {
        prepareTestListing();

        setValidInput();
        presenter.saveChanges();

        Assert.assertNotEquals(testPrice, presenter.getPrice());

        Assert.assertNotEquals(testLocation, presenter.getLocation());

        Assert.assertNotEquals(testSquareMeters, presenter.getSQM());

        Assert.assertNotEquals(testBedrooms, presenter.getBeds());

        Assert.assertNotEquals(testBathrooms, presenter.getBaths());

        Assert.assertNotEquals(testFloor, presenter.getFloor());

        Assert.assertNotEquals(testHeating, presenter.getHeat());

        Assert.assertNotEquals(testDescription, presenter.getDescription());
    }

    private void setValidInput() {
        view.setPrice(testPrice + 1);
        view.setLocation(testLocation + " changed");
        view.setSquareMeters(testSquareMeters + 1);
        view.setBedrooms(testBedrooms + 1);
        view.setBathrooms(testBathrooms + 1);
        view.setFloor(testFloor + 1);
        view.setHeating(!testHeating);
        view.setDescription(testDescription + " changed");
    }

    private void setEmptyInput() {
        view.setPrice(ModifyListingActivity.EMPTY_TEXT_FIELD);
        view.setLocation("");
        view.setSquareMeters(ModifyListingActivity.EMPTY_TEXT_FIELD);
        view.setBedrooms(ModifyListingActivity.EMPTY_TEXT_FIELD);
        view.setBathrooms(ModifyListingActivity.EMPTY_TEXT_FIELD);
        view.setFloor(ModifyListingActivity.EMPTY_TEXT_FIELD);
        view.setHeating(testHeating);
        view.setDescription("");
    }
}
