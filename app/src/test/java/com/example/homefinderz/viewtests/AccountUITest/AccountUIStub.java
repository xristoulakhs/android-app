package com.example.homefinderz.viewtests.AccountUITest;

import com.example.homefinderz.view.accountUI.AccountUIPresenter;
import com.example.homefinderz.view.accountUI.AccountUIView;

public class AccountUIStub implements AccountUIView {
    String email;
    AccountUIPresenter presenter;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPresenter(AccountUIPresenter presenter) {
        this.presenter = presenter;
    }
}
