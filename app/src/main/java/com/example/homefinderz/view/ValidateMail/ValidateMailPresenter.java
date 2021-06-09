package com.example.homefinderz.view.ValidateMail;

public class ValidateMailPresenter {

    private ValidateMailView view;

    private int generatedCode;

    private boolean validationSuccess; //boolean variable for testing purposes

    /**
     * ValidateMailPresenter constructor.
     * @param view ValidateMailView instance
     */
    public ValidateMailPresenter(ValidateMailView view) {
        this.view = view;
    }

    /**
     * checks if the inserted code matches the given code
     */
    public void checkCode() {
        if(view.getCode() == generatedCode) {
            validationSuccess = true;
            view.validated();
        }
        else {
            validationSuccess = false;
            view.showErrorMessage();
        }
    }

    public void generateCode() {
        generatedCode = 123456;
        view.sendCode(generatedCode);
    }

    public boolean isValidationSuccess() {
        return validationSuccess;
    }
}