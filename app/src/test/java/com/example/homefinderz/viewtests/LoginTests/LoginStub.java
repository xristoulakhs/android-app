package com.example.homefinderz.viewtests.LoginTests;

import com.example.homefinderz.view.login.LoginPresenter;
import com.example.homefinderz.view.login.LoginView;

public class LoginStub implements LoginView {

    String userEmail, password;
    LoginPresenter presenter;

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public String getEmail() {
        return userEmail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void showSuccessMessage() {

    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }
}
