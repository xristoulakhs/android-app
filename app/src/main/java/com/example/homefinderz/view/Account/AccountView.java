package com.example.homefinderz.view.Account;

public interface AccountView {

    void showErrorMessage(String message);

    void showSuccessMessage();

    String getEmail();

    String getPass();

    String getPassConfirm();

    String getFirstName();

    String getLastName();

    void setEmail(String email);

    void setPass(String pass);

    void setPassConfirm(String pass);

    void setFirstName(String name);

    void setLastName(String name);

    void validateMail(String email);

    void endView();
}
