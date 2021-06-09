package com.example.homefinderz.viewtests.modifyaccounttests;

import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.modifyAccount.ModifyAccountPresenter;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModifyAccountPresenterTest {

    private static ModifyAccountPresenter presenter;
    private static ModifyAccountViewStub view;
    private static int accountLoggedIn;

    /*
     * a string that will pass the
     * email pattern check
     * to be used in the tests where other
     * fields of the account are invalid
     */
    private static String testEmail = "email@fortests.com";


    @BeforeClass
    public static void setUp(){
        new MemoryInitializer().prepareData();
        view = new ModifyAccountViewStub();
    }

    @Before
    public void reset(){
        presenter = new ModifyAccountPresenter(view);
        accountLoggedIn = new AccountDAO().findAccount("chrismano@gmail.com").getId();
        presenter.setAccountLoggedIn(accountLoggedIn);
        view.setPresenter(presenter);
    }

    @Test
    public void modifyValidEmail() {
        setEmptyInput();
        view.setEmail("emailValidTest@provider.com");

        presenter.saveChanges();
        Assert.assertEquals("emailValidTest@provider.com", new AccountDAO().findAccount(accountLoggedIn).getEmail());
    }

    @Test
    public void emailInvalidTest() {
        setEmptyInput();
        String oldEmail = new AccountDAO().findAccount(accountLoggedIn).getEmail();

        //Setting in a String that is not an Email shouldn't change it
        view.setEmail("thisIsNotAnEmail");
        presenter.saveChanges();
        Assert.assertEquals(oldEmail, new AccountDAO().findAccount(accountLoggedIn).getEmail());

        // The same should happen if the user doesn't fill in their email
        view.setEmail("");
        presenter.saveChanges();
        Assert.assertEquals(oldEmail, new AccountDAO().findAccount(accountLoggedIn).getEmail());

        // The same should happen if the user enters an email that is already in use
        view.setEmail("bestcommissioner@gmail.com");
        presenter.saveChanges();
        Assert.assertEquals(oldEmail, new AccountDAO().findAccount(accountLoggedIn).getEmail());
    }

    @Test
    public void modifyOneInvalid() {
        setValidInput();

        //Trying to modify the account while either the Email or the Password are not
        //empty but are invalid, should not change the other fields even if they are valid
        String oldName = new AccountDAO().findAccount(accountLoggedIn).getUserName();

        view.setEmail("notAnEmail");
        presenter.saveChanges();
        Assert.assertEquals(oldName, new AccountDAO().findAccount(accountLoggedIn).getUserName());

        view.setEmail("email@mail.net");
        view.setPassConfirm("");
        presenter.saveChanges();
        Assert.assertEquals(oldName, new AccountDAO().findAccount(accountLoggedIn).getUserName());
    }

    @Test
    public void deleteCurrentAccount(){
        presenter.setAccountLoggedIn(new AccountDAO().findAccount("elena@gmail.com").getId());
        presenter.deleteAccount();
        Assert.assertNull(new AccountDAO().findAccount("elena@gmail.com"));
    }

    /**
     * Helper method to simulate the user entering all the fields required
     */
    private void setValidInput() {
        view.setFirstName("First Name");
        view.setLastName("Last Name");
        view.setPass("pass");
        view.setPassConfirm("pass");
    }

    /**
     * Helper method to simulate the user entering all the fields required
     */
    private void setEmptyInput() {
        view.setEmail("");
        view.setFirstName("");
        view.setLastName("");
        view.setPass("");
        view.setPassConfirm("");
    }
    
}
