package com.example.homefinderz.viewtests.validatemailtests;

import com.example.homefinderz.view.ValidateMail.ValidateMailView;

public class ValidateMailViewStub implements ValidateMailView {
    private int code;
    @Override
    public void validated() {
        //stub Normally sets the result and sends it back to SignUpActivity
    }

    @Override
    public void sendCode(int code) {
        //stub Normally prints the code to the screen
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void showErrorMessage() {
        ///stub Normally shows error message to the user
    }
}
