package com.example.homefinderz.view.dao;

import com.example.homefinderz.model.Account;

public class MemoryInitializer extends Initializer {
    @Override
    protected void eraseData() {
        for (Account acc : getDAO().getFreeAccounts()) {
            getDAO().removeAccount(acc);
        }
        for (Account acc : getDAO().getPremAccounts()) {
            getDAO().removeAccount(acc);
        }
    }

    @Override
    public AccountDAO getDAO() {
        return new AccountDAO();
    }
}
