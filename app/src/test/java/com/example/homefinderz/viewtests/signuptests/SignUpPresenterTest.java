package com.example.homefinderz.viewtests.signuptests;

import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.signUp.SignUpPresenter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SignUpPresenterTest {

    private static SignUpPresenter presenter;
    private static SignUpStub signUpStub;

    private static String testEmail = "email@fortests.com"; // a string that will pass the
                                                            // email pattern check
                                                            // to be used in the tests where other
                                                            // fields of the account are invalid


    @BeforeClass
    public static void setUp(){
        new MemoryInitializer().prepareData();
        signUpStub = new SignUpStub();
        presenter = new SignUpPresenter(signUpStub);
        signUpStub.setPresenter(presenter);
    }

    /**
     * checks if the created acc has been added at the dao
     *
     * validated is the email returned for the ValidateMailActivity
     * Therefore it is safe to create an Account with that email
     */
    @Test
    public void accCreate(){
        String validated = "testmail@email.net";
        int initialNumOfAccounts = new AccountDAO().getNumOfAccounts();
        presenter.createAccount(validated);
        Assert.assertEquals(initialNumOfAccounts + 1, new AccountDAO().getNumOfAccounts());
    }

    @Test
    public void signUpValid() {
        // Calling the presenter's onSignUp after setting the email to a string that matches
        // an email's pattern, should create a new account
        setValidInput();
        int numOfAccountsBefore = new AccountDAO().getNumOfAccounts();
        signUpStub.setEmail("emailValidTest@provider.com");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore + 1, new AccountDAO().getNumOfAccounts());
    }
    /**
     * This tests if the string the user entered is an email address
     */
    @Test
    public void emailInvalidTest() {
        setValidInput();
        String invalidEmail = "thisIsNotAnEmail";
        int numOfAccountsBefore = new AccountDAO().getNumOfAccounts();

        // Calling the presenter's onSignUp after setting the email to a string that doesn't match
        // an email's pattern, should NOT create a new account
        signUpStub.setEmail(invalidEmail);
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());

        // The same should happen if the user doesn't fill in their email
        signUpStub.setEmail("");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());

        // The same should happen if the user enters an email that is already in use
        signUpStub.setEmail("bestcommissioner@gmail.com");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());
    }

    @Test
    public void passwordInvalidTest() {
        setValidInput();
        signUpStub.setEmail(testEmail);
        int numOfAccountsBefore = new AccountDAO().getNumOfAccounts();

        //Calling onSignUp after setting an empty password shouldn't change the number of Accounts
        signUpStub.setPass("");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());
    }

    @Test
    public void passwordConfirmInvalidTest() {
        setValidInput();
        signUpStub.setEmail(testEmail);
        int numOfAccountsBefore = new AccountDAO().getNumOfAccounts();

        //Calling onSignUp after setting an empty password shouldn't change the number of Accounts
        signUpStub.setPassConfirm("this is not the password");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());
    }

    @Test
    public void firstNameInvalidTest() {
        setValidInput();
        signUpStub.setEmail(testEmail);
        int numOfAccountsBefore = new AccountDAO().getNumOfAccounts();

        //Calling onSignUp after setting an empty first name shouldn't change the number of Accounts
        signUpStub.setFirstName("");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());
    }
    @Test
    public void lastNameInvalidTest() {
        setValidInput();
        signUpStub.setEmail(testEmail);
        int numOfAccountsBefore = new AccountDAO().getNumOfAccounts();

        //Calling onSignUp after setting an empty first name shouldn't change the number of Accounts
        signUpStub.setLastName("");
        presenter.onSignUp();
        Assert.assertEquals(numOfAccountsBefore, new AccountDAO().getNumOfAccounts());
    }

    /**
     * Helper method to simulate the user entering all the fields required
     */
    private void setValidInput() {
        signUpStub.setFirstName("First Name");
        signUpStub.setLastName("Last Name");
        signUpStub.setPass("pass");
        signUpStub.setPassConfirm("pass");
    }

}
