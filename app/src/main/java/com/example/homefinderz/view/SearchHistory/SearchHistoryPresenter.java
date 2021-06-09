package com.example.homefinderz.view.SearchHistory;

import com.example.homefinderz.model.ArchivedSearch;
import com.example.homefinderz.view.dao.AccountDAO;

import java.util.List;

public class SearchHistoryPresenter {

    private int accountID;
    private SearchHistoryView view;
    private AccountDAO accountDAO;

    public SearchHistoryPresenter(SearchHistoryView view) {
        this.view = view;
        accountDAO = new AccountDAO();
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public List<ArchivedSearch> getSearchHistory() {
        return accountDAO.findAccount(accountID).getArchivedSearches();
    }
}
