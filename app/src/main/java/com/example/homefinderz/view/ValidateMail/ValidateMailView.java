package com.example.homefinderz.view.ValidateMail;

public interface ValidateMailView {
    void validated();
    void sendCode(int code);
    void setCode(int code);
    int getCode();

    void showErrorMessage();
}
