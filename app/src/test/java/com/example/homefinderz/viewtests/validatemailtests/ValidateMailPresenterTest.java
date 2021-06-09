package com.example.homefinderz.viewtests.validatemailtests;

import com.example.homefinderz.view.ValidateMail.ValidateMailPresenter;
import com.example.homefinderz.view.ValidateMail.ValidateMailView;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidateMailPresenterTest {

    private static ValidateMailPresenter presenter;
    private static ValidateMailView view;

    @BeforeClass
    public static void setUp(){
        view = new ValidateMailViewStub();
        presenter = new ValidateMailPresenter(view);
    }

    /**
     * uses the methods
     */
    @Test
    public void correctCode() {
        presenter.generateCode();
        view.setCode(123456);

        presenter.checkCode();
        Assert.assertTrue(presenter.isValidationSuccess());
    }

    @Test
    public void incorrectCode() {
        presenter.generateCode();
        view.setCode(121212);

        presenter.checkCode();
        Assert.assertFalse(presenter.isValidationSuccess());
    }
}
