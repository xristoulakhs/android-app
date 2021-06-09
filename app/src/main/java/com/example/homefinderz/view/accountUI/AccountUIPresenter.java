package com.example.homefinderz.view.accountUI;

import com.example.homefinderz.model.Account;
import com.example.homefinderz.view.dao.AccountDAO;

public class AccountUIPresenter {

    private AccountUIView view;
    private AccountDAO dao;

    private int accountID;

    /**
     * Initializes the AccountUIPresenter.
     * @param view An instance view
     */
    public AccountUIPresenter(AccountUIView view){
        this.view = view;
        dao = new AccountDAO();
    }

    /**
     * This method checks if the current account is premium or not
     * @return 1 if account is premium, 0 if it is not
     */
    public int checkPremium(){
        Account currentAcc = dao.findAccount(accountID);
        if(currentAcc.isPremium()){
            return 1;
        }
        else{
            currentAcc.makePremium();
            dao.update(currentAcc);
            return 0;
        }
    }

    /**
     * Finds an account when given an account ID
     * @return the account that corresponds to the current ID
     */
    public Account getAcc(){
        return dao.findAccount(accountID);
    }

    /**
     * This method declares an account as the current logged in account
     * @param accountID the account that logged in
     */
    public void setAccountLoggedIn(int accountID) {
        this.accountID = accountID;
    }

    /**
     * This method retrieves the current's account ID
     * @return the current user's account ID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * This method informs if the current account is deleted or not
     * @return true if the current account cannot be found in database,
     *         false if the current account exists in database
     */
    public boolean accountDeleted() {
        return dao.findAccount(accountID) == null;
    }
}
