//package com.example.homefinderz.main;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.util.Map;
//
//import static com.example.homefinderz.main.testHelper_PassOrFail.BATHROOMS;
//import static com.example.homefinderz.main.testHelper_PassOrFail.BEDROOMS;
//import static com.example.homefinderz.main.testHelper_PassOrFail.EMAIL_DUPE;
//import static com.example.homefinderz.main.testHelper_PassOrFail.EMAIL_DUPE2;
//import static com.example.homefinderz.main.testHelper_PassOrFail.PASSWORD;
//import static com.example.homefinderz.main.testHelper_PassOrFail.PRICE;
//import static com.example.homefinderz.main.testHelper_PassOrFail.SQM;
//import static com.example.homefinderz.main.testHelper_PassOrFail.SURNAME;
//import static com.example.homefinderz.main.testHelper_PassOrFail.VALID;
//import static org.junit.Assert.*;
//
//public class UnitTest_AccountController {
//
//    static int accountCounter = 0;
//
//    static String base_name    = "Name";
//    static String base_surname = "Surname";
//    static String base_email   = "email@provider.com";
//    static String base_email2  = "email@notprovider.com";
//    static String base_pass    = "Password1";
//
//    static AccountController controller;
//
//    /*
//     * The following arrays are there to help dictate successive Account creations and Listing creations
//     *
//     * accountIDs_Free and accountIDs_Premium hold the indexes of Accounts in the ArrayLists that hold the Accounts.
//     * They dictate which account will publish the Listing each time. Where there is 0, it means the Listing
//     * is meant to have incorrect data during creation and therefore fail to be created.
//     *
//     * the only exception being the accountIDs_Free[1]. It is on purpose to actually try and publish a listing with
//     * incorrect data
//     */
//    static int[] accountIDs_Free    = {11, 11, 11, 0, 13, 0, 9, 9, 12, 0, 11};
//    static int[] accountIDs_Premium = {3, 5, 0, 4};
//    static testHelper_PassOrFail[] passFail_Accounts = {EMAIL_DUPE, EMAIL_DUPE, VALID, SURNAME,
//            VALID, EMAIL_DUPE, VALID, VALID, VALID, PASSWORD, EMAIL_DUPE2, EMAIL_DUPE2, VALID, VALID, VALID, VALID};
//    static testHelper_PassOrFail[] passFail_Listings = {VALID, VALID, PRICE, PRICE, VALID, SQM, VALID,
//            VALID, VALID, BEDROOMS, VALID, VALID, VALID, BATHROOMS, VALID};
//
//    /**
//     * Bring the app in an expected running state
//     * By which we mean: multiple users, some of which are premium, and multiple Listings
//     */
//    @BeforeClass
//    public static void setUp() {
//        controller = new AccountController();
//        setUpMultipleAccounts();
//        upgradeMultipleAccounts();
//        createMultipleListings();
//        controller.makeAccount(base_name,base_surname, base_email,base_pass);
//    }
//    public static void setUpMultipleAccounts() {
//
//        for (int i = 0; i < passFail_Accounts.length; i++) {
//            accountCounter++;
//            String counterString = Integer.toString(accountCounter);
//            String  accEmail;
//            if(passFail_Accounts[i] == EMAIL_DUPE2) {
//                accEmail = base_email2;
//            } else if(passFail_Accounts[i] == EMAIL_DUPE) {
//                accEmail = base_email;
//            } else {
//                accEmail = counterString + base_email;
//            }
//            switch (passFail_Accounts[i]) {
//                case VALID:
//                case EMAIL_DUPE:
//                case EMAIL_DUPE2:
//                    controller.makeAccount(counterString + base_name,counterString + base_surname, accEmail,counterString + base_pass);
//                    break;
//                case NAME:
//                    controller.makeAccount("",counterString + base_surname, accEmail,counterString + base_pass);
//                    break;
//                case SURNAME:
//                    controller.makeAccount(counterString + base_name,"", accEmail, counterString + base_pass);
//                    break;
//                case EMAIL_BLANK:
//                    controller.makeAccount(counterString + base_name,counterString + base_surname, "",counterString + base_pass);
//                    break;
//                case PASSWORD:
//                    controller.makeAccount(counterString + base_name,counterString + base_surname, counterString + base_email,"");
//                    break;
//            }
//        }
//
//        /**
//         * deleteProfile
//         */
//        controller.login(base_email2, "11Password1", false);
//        controller.deleteProfile(true);
//        assertNull(controller.getAccountByID(8));
//    }
//    public static void upgradeMultipleAccounts() {
//
//        Account acc = controller.getAccountByID(0);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        controller.upgradeUser();
//        assertTrue(controller.getCurrentAccount().isPremium());
//
//        acc = controller.getAccountByID(5);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        controller.upgradeUser();
//        assertTrue(controller.getCurrentAccount().isPremium());
//        acc = controller.getAccountByID(10);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        controller.upgradeUser();
//        assertTrue(controller.getCurrentAccount().isPremium());
//        acc = controller.getAccountByID(4);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        controller.upgradeUser();
//        assertTrue(controller.getCurrentAccount().isPremium());
//
//        controller.login(base_email, "1Password1", false);
//        controller.deleteProfile(false);
//        controller.deleteProfile(true);
//
//        assertNull(controller.getAccountByID(0));
//    }
//    public static void createMultipleListings() {
//        int j = 0;
//        for(int i = 0; i < accountIDs_Free.length; i++) {
//            if(controller.getAccountByID(accountIDs_Free[i]) != null) {
//                controller.login(controller.getAccountByID(accountIDs_Free[i]).getEmail(), controller.getAccountByID(accountIDs_Free[i]).getPassword(), false);
//                switch (passFail_Listings[j]) {
//                    case VALID:
//                        controller.publish_Listing(300, "Athens", 60, 1, 1, 1, true, "add description");
//                        break;
//                    case PRICE:
//                        controller.publish_Listing(0, "Athens", 60, 1, 1, 1, false, "add description");
//                        break;
//                    case SQM:
//                        controller.publish_Listing(300, "Athens", 0, 1, 1, 1, false, "add description");
//                        break;
//                    case BEDROOMS:
//                        controller.publish_Listing(300, "Athens", 60, 0, 1, 1, false, "add description");
//                        break;
//                    case BATHROOMS:
//                        controller.publish_Listing(300, "Athens", 60, 1, 0, 1, false, "add description");
//                        break;
//                }
//            }
//            j++;
//        }
//
//        for(int i = 0; i < accountIDs_Premium.length; i++) {
//            if(controller.getAccountByID(accountIDs_Premium[i]) != null) {
//                controller.login(controller.getAccountByID(accountIDs_Premium[i]).getEmail(), controller.getAccountByID(accountIDs_Premium[i]).getPassword(), false);
//                switch (passFail_Listings[j]) {
//                    case VALID:
//                        controller.publish_Listing(300, "Athens", 60, 1, 1, 1, true, "add description");
//                        break;
//                    case PRICE:
//                        controller.publish_Listing(0, "Athens", 60, 1, 1, 1, false, "add description");
//                        break;
//                    case SQM:
//                        controller.publish_Listing(300, "Athens", 0, 1, 1, 1, false, "add description");
//                        break;
//                    case BEDROOMS:
//                        controller.publish_Listing(300, "Athens", 60, 0, 1, 1, false, "add description");
//                        break;
//                    case BATHROOMS:
//                        controller.publish_Listing(300, "Athens", 60, 1, 0, 1, false, "add description");
//                        break;
//                }
//            }
//            j++;
//        }
//    }
//    @Before
//    public void loginBaseAccount() {
//        controller.login(base_email, base_pass, true);
//    }
//
//    @Test
//    public void login() {
//        assertNotNull(controller.getCurrentAccount());
//        controller.logOut();
//        assertNull(controller.getCurrentAccount());
//        assertTrue(controller.autoLogin());
//        assertFalse(controller.login("failure" + base_email, base_pass, true));
//        assertFalse(controller.login(base_email, "failure" + base_pass, true));
//        assertTrue(controller.autoLogin());
//        controller.login(base_email, base_pass, true);
//        controller.setSavedPassword("wrongPassword");
//        assertFalse(controller.autoLogin());
//        assertFalse(controller.autoLogin());
//    }
//
//    @Test
//    public void modifyUser() {
//        assertEquals(base_email, controller.getCurrentAccount().getEmail());
//        controller.modifyUser("newEmail@provider.com", modifyField.EMAIL, 1);
//        assertEquals(base_email, controller.getCurrentAccount().getEmail());
//        controller.modifyUser("newEmail@provider.com", modifyField.EMAIL, 0);
//        assertEquals("newEmail@provider.com", controller.getCurrentAccount().getEmail());
//        controller.modifyUser("newPass", modifyField.PASSWORD, 0);
//        assertEquals("newPass", controller.getCurrentAccount().getPassword());
//        controller.modifyUser("newName", modifyField.USERNAME, 0);
//        assertEquals("newName", controller.getCurrentAccount().getUserName());
//        controller.modifyUser("newSurname", modifyField.USERSURNAME, 0);
//        assertEquals("newSurname", controller.getCurrentAccount().getUserSurname());
//        controller.getCurrentAccount().setId(800); //normally we wouldn't want/be able to change a user's ID
//        assertEquals(800, controller.getCurrentAccount().getId());
//
//
//        int counter = controller.getActiveAccountCounter();
//        controller.makeAccount(base_name,base_surname, base_email,base_pass);
//        assertEquals(counter + 1, controller.getActiveAccountCounter());
//    }
//
//    @Test
//    public void publish_Listing() {
//        assertNotNull(controller.getCurrentAccount());
//        int lisCount = controller.displayListings().size();
//        controller.publish_Listing(300, "Athens", 60, 1, 1, 1, false, "add description");
//        assertEquals(lisCount + 1, controller.displayListings().size());
//    }
//
//    @Test
//    public void displayListings() {
//        Account acc = controller.getAccountByID(3);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        assertNotNull(controller.displayListings());
//    }
//
//    @Test
//    public void modifyListing() {
//        Account acc = controller.getAccountByID(11);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        int lis_id = controller.displayListings().get(0).getId();
//        controller.modifyListing(lis_id, 500, modifyField.PRICE);
//        assertEquals(500, controller.displayListings().get(0).getPrice());
//
//        controller.modifyListing(lis_id, "Rhodes", modifyField.LOCATION);
//        assertEquals("Rhodes", controller.displayListings().get(0).getLocation());
//
//        controller.modifyListing(lis_id, 4, modifyField.FLOOR);
//        assertEquals(4, controller.displayListings().get(0).getFloor());
//
//        boolean oldHeating = controller.displayListings().get(0).getHeating();
//        controller.modifyListing(lis_id, "false but it doesn't matter here", modifyField.HEATING);
//        assertEquals(!oldHeating, controller.displayListings().get(0).getHeating());
//
//        controller.modifyListing(lis_id, "Great place lots of butterflies!", modifyField.DESCRIPTION);
//        assertEquals("Great place lots of butterflies!", controller.displayListings().get(0).getDescription());
//
//        controller.modifyListing(lis_id, 4, modifyField.BEDROOMS);
//        assertEquals(4, controller.displayListings().get(0).getBedrooms());
//
//        controller.modifyListing(lis_id, 2, modifyField.BATHROOMS);
//        assertEquals(2, controller.displayListings().get(0).getBathrooms());
//
//        controller.modifyListing(lis_id, 800, modifyField.SQM);
//        assertEquals(800, controller.displayListings().get(0).getSquareFeet());
//    }
//
//    @Test
//    public void deleteListing() {
//        Account acc = controller.getAccountByID(11);
//        controller.login(acc.getEmail(), acc.getPassword(), false);
//        int lis_id = 0;
//        int wrongLis_id = 19;
//        int lisCounter =  controller.displayListings().size();
//        try {
//            controller.deleteListing(wrongLis_id);
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//        assertEquals(lisCounter, controller.displayListings().size());
//        try {
//            controller.deleteListing(lis_id);
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//        assertEquals(lisCounter - 1, controller.displayListings().size());
//    }
//
//    @Test
//    public void SearchAndApprove() {
//        Search newSearch = new Search(1,100,5000,"Athens",800,30,1,1,1,true);
//        Map<Integer, Listing> results = controller.doSearch(newSearch, true);
//        assertEquals(1, controller.searchesRequest().size());
//        assertEquals(1, controller.getArchivedSearches().size());
//        controller.deleteSearch(newSearch);
//        assertEquals(0, controller.searchesRequest().size());
//        assertEquals(1, controller.getArchivedSearches().size());
//        newSearch.setHeat(false);
//        results = controller.doSearch(newSearch, true);
//        assertEquals(1, controller.searchesRequest().size());
//        assertEquals(2, controller.getArchivedSearches().size());
//
//        Search emptySearch = new Search();
//        emptySearch.setSearchId(2);
//        emptySearch.setMinPrice(10);
//        emptySearch.setMaxPrice(50);
//        emptySearch.setLocation("New York");
//        emptySearch.setMaxSqm(900);
//        emptySearch.setMinSqm(100);
//        emptySearch.setBedrooms(5);
//        emptySearch.setBathrooms(5);
//        emptySearch.setFloor(8);
//        emptySearch.setHeat(false);
//
//        Map<Integer, Listing> resultsEmpty = controller.doSearch(emptySearch, false);
//        assertTrue(resultsEmpty.isEmpty());
//        assertEquals(1, controller.searchesRequest().size());
//        assertEquals(3, controller.getArchivedSearches().size());
//
//        assertEquals(9, results.size());
//
//        boolean approveHalf = false;
//        for(Map.Entry<Integer, Listing> pair : results.entrySet()) {
//            if(approveHalf) {
//                controller.listingApprove(pair.getKey(), pair.getValue().getId());
//            }
//            approveHalf = !approveHalf;
//        }
//
//        assertEquals(4, controller.getCurrentAccount().getApprovedListings().size());
//
//        for(ArchivedSearch history: controller.getArchivedSearches()) {
//            assertNotNull(history.getSearchTime());
//        }
//    }
//
//}