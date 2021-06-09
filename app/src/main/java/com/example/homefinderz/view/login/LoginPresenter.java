package com.example.homefinderz.view.login;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.view.dao.AccountDAO;

public class LoginPresenter {
    LoginView view;

    private AccountDAO accounts;
    private int accountID;


    /**
     * LoginPresenter constructor.
     * @param view  LoginView instance
     */
    public LoginPresenter(LoginView view) {
        this.view = view;
        accounts = new AccountDAO();
    }

    /**
     * This method checks the user's credentials and logs the user in
     * @return 1 if the login is successful 0 otherwise
     */
    public int login(){
        String email=view.getEmail();
        String password=view.getPassword();

        Account userAccount = accounts.findAccount(email);

        if(userAccount!=null){
            if(userAccount.getPassword().equals(password)){
                accountID = userAccount.getId();
                view.showSuccessMessage();
                return 1;
            }
        }
        return 0;
    }

    /**
     * This method retrieves the current's account ID
     * @return the current user's account ID
     */
    public int getAccountID() {
        return accountID;
    }
}
