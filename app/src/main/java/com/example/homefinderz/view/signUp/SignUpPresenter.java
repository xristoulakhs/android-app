package com.example.homefinderz.view.signUp;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.view.Account.AccountView;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.regex.Pattern;

public class SignUpPresenter {

    private AccountView view;
    private AccountDAO accountDAO;

    private String pass;
    private String firstName;
    private String lastName;

    /**
     * SignUpPresenter constructor.
     * @param view view instance
     */
    public SignUpPresenter(AccountView view) {
        this.view = view;
        this.accountDAO = new AccountDAO();
    }

    public void onSignUp() {
        String email = view.getEmail();
        pass = view.getPass();
        String passConfirm = view.getPassConfirm();
        firstName = view.getFirstName();
        lastName  = view.getLastName();

        if (email.equals(""))
            view.showErrorMessage("Please fill in your email!");
        else if(!isEmailValid(email))
            view.showErrorMessage("Please fill in a valid email address!");
        else if(accountDAO.findAccount(email) != null)
            view.showErrorMessage("An account with this address already exists!");
        else if(pass.equals(""))
            view.showErrorMessage("Please fill choose a password!");
        else if(passConfirm.equals("") || !pass.equals(passConfirm))
            view.showErrorMessage("Confirm password must match password!");
        else if(firstName.equals(""))
            view.showErrorMessage("Please fill in your first name!");
        else if(lastName.equals(""))
            view.showErrorMessage("Please fill in your last name!");
        else {
            view.validateMail(email);
        }
    }

    public void createAccount(String validated) {
        accountDAO.add(new Account(accountDAO.createAccountID(), firstName,
                lastName, validated, pass));
        view.showSuccessMessage();
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
}
