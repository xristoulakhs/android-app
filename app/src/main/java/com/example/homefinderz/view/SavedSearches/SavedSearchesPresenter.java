package com.example.homefinderz.view.SavedSearches;

import com.example.homefinderz.model.Search;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.List;

public class SavedSearchesPresenter {

    private AccountDAO accountDAO;
    private SavedSearchesView view;
    private int accountID;

    public SavedSearchesPresenter(SavedSearchesView view) {
        this.view = view;
        accountDAO = new AccountDAO();
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public List<Search> getStoredSearches() {
        return accountDAO.findAccount(accountID).getStoredSearches();
    }

    public void deleteSearch(int searchId) {
        accountDAO.removeSearch(accountID, searchId);
    }

    public int getAccountID() {
        return accountID;
    }
}
