package com.example.homefinderz.viewtests.LoginTests;


import com.example.homefinderz.model.Account;
import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.login.LoginPresenter;
import com.example.homefinderz.view.login.LoginView;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginPresenterTest {

    static LoginPresenter presenter;
    static LoginView stub;

    @BeforeClass
    public static void setUp(){
        new MemoryInitializer().prepareData();
        stub =new LoginStub();
        presenter= new LoginPresenter(stub);
        stub.setPresenter(presenter);
    }

    /**
     * checks if the account has logged in successfully
     */
    @Test
    public void loginExistingAccount(){
        stub.setUserEmail("jkalo@gmail.com");
        stub.setPassword("password");

        int result = presenter.login();
        Assert.assertEquals(1,result);
    }

    @Test
    public void loginNotExistingAccount(){
        stub.setUserEmail("wrong@gmail.com");
        stub.setPassword("password");

        int result = presenter.login();
        Assert.assertEquals(0,result);
    }

    /**
     * Checks that the retrieved ID is the expected one
     */
    @Test
    public void checkLoggedInID(){
        AccountDAO accountDAO = new AccountDAO();
        Account testAccount = new Account(accountDAO.createAccountID(),
                "TestName", "TestLastName",
                "test@gmail.com", "test");
        accountDAO.add(testAccount);
        int ID = testAccount.getId();

        stub.setUserEmail("test@gmail.com");
        stub.setPassword("test");

        presenter.login();
        Assert.assertEquals(ID,presenter.getAccountID());
    }
}
