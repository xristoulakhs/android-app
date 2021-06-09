package com.example.homefinderz.view.modifyAccount;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.view.Account.AccountView;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.regex.Pattern;


public class ModifyAccountPresenter {

    private AccountView view;

    private boolean validatedMail = false;
    private AccountDAO accountDAO;
    private Account userAccount;
    private int accountID;

    /**
     * Instantiates the ModifyAccountPresenter.
     * @param view An instance of the view
     */
    public ModifyAccountPresenter(AccountView view) {
        this.view = view;
        accountDAO = new AccountDAO();
    }

    public void saveChanges() {
        String email = view.getEmail();
        String pass = view.getPass();
        String passConfirm = view.getPassConfirm();
        String firstName = view.getFirstName();
        String lastName  = view.getLastName();

        boolean modifySuccess = true;

        if (!email.equals("") && !isEmailValid(email)) {
            view.showErrorMessage("Please fill in a valid email address!");
            modifySuccess = false;
        }
        if (!pass.equals("") && !pass.equals(passConfirm)) {
            view.showErrorMessage("Passwords must match!");
            modifySuccess = false;
        }

        if(modifySuccess) {
            if (!email.equals("") && !email.equals(userAccount.getEmail())) {
                view.validateMail(email);
            }
            if (!pass.equals("") && !pass.equals(userAccount.getPassword())) {
                userAccount.setPassword(pass);
            }
            if (!firstName.equals("") && !firstName.equals(userAccount.getUserName())) {
                userAccount.setUserName(firstName);
            }
            if (!lastName.equals("") && !pass.equals(userAccount.getUserSurname())) {
                userAccount.setUserSurname(lastName);
            }
            accountDAO.update(userAccount);
        }
    }

    public void saveChangedEmail(String validated) {
        accountDAO.findAccount(accountID).setEmail(validated);
    }

    /**
     * Compares a String with a reg exp pattern that matches normal email addresses
     * @param email the string to be checked
     * @return Whether the string is an email address or not
     */
    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return pattern.matcher(email).matches();
    }

    public String getUserEmail() {
        return userAccount.getEmail();
    }

    public String getUserFirstName() {
        return userAccount.getUserName();
    }

    public String getUserLastName() {
        return userAccount.getUserSurname();
    }

    public void deleteAccount() {
        //todo: make a popup asking the user if they really want to delete their account

        accountDAO.removeAccount(userAccount);
        view.endView();
    }

    public void setAccountLoggedIn(int accountID) {
        this.accountID = accountID;
        userAccount = accountDAO.findAccount(accountID);
    }
}
