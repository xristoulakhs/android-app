package com.example.homefinderz.model;

import com.example.homefinderz.view.dao.AccountDAO;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.List;

import static com.example.homefinderz.model.testHelper_PassOrFail.EMAIL_DUPE;
import static com.example.homefinderz.model.testHelper_PassOrFail.EMAIL_DUPE2;
import static com.example.homefinderz.model.testHelper_PassOrFail.PASSWORD;
import static com.example.homefinderz.model.testHelper_PassOrFail.SURNAME;
import static com.example.homefinderz.model.testHelper_PassOrFail.VALID;
import static org.junit.Assert.*;

public class UnitTest_Account {

    static int accountCounter = 0;

    static String base_name    = "Name";
    static String base_surname = "Surname";
    static String base_email   = "email@provider.com";
    static String base_email2  = "email@notprovider.com";
    static String base_pass    = "Password1";

    static testHelper_PassOrFail[] passFail = {EMAIL_DUPE, EMAIL_DUPE, VALID, SURNAME, VALID, EMAIL_DUPE, VALID, VALID, VALID, PASSWORD, EMAIL_DUPE2, EMAIL_DUPE2, VALID, VALID};

    static Account testHelperAcc;
    static AccountDAO container;



    @BeforeClass
    public static void setUp() {
        container = new AccountDAO();
        if(container.findAccount(base_email) == null) {
            testHelperAcc = Account.createAccount(accountCounter, base_name, base_surname, base_email, base_pass);
        }
    }

    @Test
    public void makeAccount_fail() {
        accountCounter++;
        String counterString = Integer.toString(accountCounter);
        assertNull(Account.createAccount(accountCounter, counterString + base_name,
                "", counterString + base_email,counterString + base_pass));
    }
    @Test
    public void makeAccount_pass() {
        accountCounter++;
        String counterString = Integer.toString(accountCounter);
        assertNotNull(Account.createAccount(accountCounter, counterString + base_name,
                counterString + base_surname, counterString + base_email,counterString + base_pass));
    }

//    @Test
//    public void makeRemoveAccounts() {
//        boolean madeAPremium = false;
//        for (int i = 0; i < passFail.length; i++) {
//            accountCounter++;
//            String counterString = Integer.toString(accountCounter);
//            String accEmail;
//            if(passFail[i] == EMAIL_DUPE2) {
//                accEmail = base_email2;
//            } else if(passFail[i] == EMAIL_DUPE) {
//                accEmail = base_email;
//            } else {
//                accEmail = counterString + base_email;
//            }
//            Account newAcc = null;
//            if(container.findAccount(accEmail) == null) {
//                switch (passFail[i]) {
//                    case VALID:
//                    case EMAIL_DUPE:
//                    case EMAIL_DUPE2:
//                        newAcc = Account.createAccount(accountCounter, counterString + base_name,
//                                counterString + base_surname, accEmail, counterString + base_pass);
//                        break;
//                    case NAME:
//                        newAcc = createAccount(accountCounter, "",
//                                counterString + base_surname, accEmail, counterString + base_pass);
//                        break;
//                    case SURNAME:
//                        newAcc = Account.createAccount(accountCounter, counterString + base_name,
//                                "", accEmail, counterString + base_pass);
//                        break;
//                    case EMAIL_BLANK:
//                        newAcc = Account.createAccount(accountCounter, counterString + base_name,
//                                counterString + base_surname, "", counterString + base_pass);
//                        break;
//                    case PASSWORD:
//                        newAcc = Account.createAccount(accountCounter, counterString + base_name,
//                                counterString + base_surname, accEmail, "");
//                        break;
//                }
//            }
//
//            if (newAcc != null) {
//               container.addFree(newAcc);
//                if(!madeAPremium) {
//                    newAcc.makePremium();
//                    container.addPrem(newAcc);
//                    if(container.getPremAccounts().size() > 1) {
//                        madeAPremium = true;
//                    }
//                }
//                //System.out.println(newAcc.getEmail());
//            }
//        }
//        assertEquals(7, container.getFreeAccounts().size());
//        assertEquals(2, container.getPremAccounts().size());
//        boolean remFreeRun = false;
//        boolean remPremRun = false;
//        int accountCounter = container.getNumOfAccounts();
//        Account acc = container.findAccount(9);
//        assertNotNull(acc);
//        acc = container.findAccount(3);  //Similar but it is there in order to cover line 73 of AccountDAO
//        assertNotNull(acc);
//        acc = container.findAccount(1);
//        assertNull(acc);
//        if(!container.getFreeAccounts().isEmpty()) {
//            int freeCounter = container.getFreeAccounts().size();
//            container.remFree(container.findAccount(base_email2));
//            remFreeRun = true;
//            assertEquals(--freeCounter, container.getFreeAccounts().size());
//        }
//        if(!container.getPremAccounts().isEmpty()) {
//            int premCounter = container.getPremAccounts().size();
//            container.remPrem(container.findAccount(base_email));
//            remPremRun = true;
//            assertEquals(--premCounter, container.getPremAccounts().size());
//        }
//        if(remFreeRun && remPremRun) {
//            assertEquals(accountCounter - 2, container.getNumOfAccounts());
//        } else if(remFreeRun || remPremRun) {
//            assertEquals(accountCounter - 1, container.getNumOfAccounts());
//        }
//    }

    @Test
    public void getSetId() {
        testHelperAcc.setId(0);
        assertEquals(0, testHelperAcc.getId());
    }


    @Test
    public void getSetUserName() {
        testHelperAcc.setUserName("Who's");
        assertEquals("Who's", testHelperAcc.getUserName());
    }

    @Test
    public void getSetUserSurname() {
        testHelperAcc.setUserSurname("aGoodBoy");
        assertEquals("aGoodBoy", testHelperAcc.getUserSurname());
        //good boy testHelperAcc is helping us test these
    }


    @Test
    public void getSetEmail() {
        testHelperAcc.setEmail(base_email);
        assertEquals(base_email, testHelperAcc.getEmail());
    }

    @Test
    public void getSetPassword() {
        testHelperAcc.setPassword("b3stP455w0rd");
        assertEquals("b3stP455w0rd", testHelperAcc.getPassword());
    }


    @Test
    public void isMakePremium() {
        /**
         * first check that newly made accounts aren't premium then make one premium
         */
        assertFalse(testHelperAcc.isPremium());
        testHelperAcc.makePremium();
        assertTrue(testHelperAcc.isPremium());
    }


    @Test
    public void listingsAccountInteraction() {
        //normally we would check if there is an account with the email but it's not important for this test
        Account testPublisher = Account.createAccount(69, "Publi", "Sher", base_email, base_pass);
        Listing newListing = Listing.createListing(6901, 100, "location", 50, 3, 1, 2, true, "description");
        int expectedPublishedSize = 0;
        if (newListing != null && testPublisher != null) {
            testPublisher.addPublishedListing(newListing);
            expectedPublishedSize++;
            assertEquals(expectedPublishedSize, testPublisher.getPublishedListings().size());
            int expectedApprovedSize = 0;
            for (Listing lis : testPublisher.getPublishedListings()) {
                testHelperAcc.approveListing(lis);
                expectedApprovedSize++;
                assertEquals(expectedApprovedSize, testHelperAcc.getApprovedListings().size());
            }

        }
    }

    @Test
    public void storedSearchesAccountInteraction() {
        Assert.assertEquals(0, testHelperAcc.getStoredSearches().size());
        Search newSearch = new Search(1, 10 ,20, "String location",
        100, 40, 1, 1, 6, false);
        int expectedStoredSearchSize = 0;
        testHelperAcc.storeSearch(newSearch);
        expectedStoredSearchSize++;
        assertEquals(expectedStoredSearchSize, testHelperAcc.getStoredSearches().size());
        List<Search> storedSearches = testHelperAcc.getStoredSearches();
        assertEquals(1, testHelperAcc.getStoredSearches().get(0).getSearchId());
    }
}