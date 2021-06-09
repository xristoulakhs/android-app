package com.example.homefinderz.viewtests.modifyaccounttests;

import com.example.homefinderz.view.Account.AccountView;
import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.modifyAccount.ModifyAccountPresenter;
import com.example.homefinderz.view.signUp.SignUpPresenter;

public class ModifyAccountViewStub implements AccountView {
    private ModifyAccountPresenter presenter;
    private String email;
    private String password;
    private String passConfirm;
    private String firstname;
    private String lastname;


    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSuccessMessage() {

    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPass(String pass) {
        this.password = pass;
    }

    @Override
    public void setPassConfirm(String pass) {
        this.passConfirm = pass;
    }

    @Override
    public void setFirstName(String name) {
        this.firstname = name;
    }

    @Override
    public void setLastName(String name) {
        this.lastname = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPass() {
        return password;
    }

    @Override
    public String getPassConfirm() {
        return passConfirm;
    }

    @Override
    public String getFirstName() {
        return firstname;
    }

    @Override
    public String getLastName() {
        return lastname;
    }

    @Override
    public void validateMail(String email) {
        if(new AccountDAO().findAccount(email) == null){
            presenter.saveChangedEmail(email);
        }
    }

    @Override
    public void endView() {

    }

    public void setPresenter(ModifyAccountPresenter presenter) {
        this.presenter = presenter;
    }

}
