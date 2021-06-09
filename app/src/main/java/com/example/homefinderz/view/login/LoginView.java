package com.example.homefinderz.view.login;

public interface LoginView {

    String getEmail();

    String getPassword();

    void setUserEmail(String email);
    void setPassword(String pass);

    void showSuccessMessage();

    void setPresenter(LoginPresenter presenter);
}
