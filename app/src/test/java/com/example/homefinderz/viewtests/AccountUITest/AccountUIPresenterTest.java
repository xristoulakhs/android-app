package com.example.homefinderz.viewtests.AccountUITest;

import com.example.homefinderz.view.accountUI.AccountUIPresenter;
import com.example.homefinderz.view.dao.AccountDAO;
import com.example.homefinderz.view.dao.Initializer;
import com.example.homefinderz.view.dao.MemoryInitializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountUIPresenterTest {

    static AccountUIPresenter presenter;
    static AccountUIStub stub;
    static Initializer initializer;

    @BeforeClass
    public static void setUp() {
        initializer = new MemoryInitializer();
        initializer.prepareData();
        stub = new AccountUIStub();
    }

    /**
     * we want to reset the presenter before each test because some tests require the
     * presenter's data to be unaffected by previous tests
     */
    @Before
    public void reset(){
        presenter = new AccountUIPresenter(stub);
        stub.setPresenter(presenter);
    }

    @Test
    public void checkPremium() {
        int id = new AccountDAO().findAccount("jkalo@gmail.com").getId();
        presenter.setAccountLoggedIn(id);
        int result = presenter.checkPremium();
        Assert.assertEquals(1, result);
    }

    @Test
    public void checkNotPremium() {
        int id = new AccountDAO().findAccount("chrismano@gmail.com").getId();
        presenter.setAccountLoggedIn(id);
        int result = presenter.checkPremium();
        Assert.assertEquals(0, result);
    }

    /**
     * Checks that a not existing account cannot be found in Database
     */
    @Test
    public void accountNotExists(){
        int id = -1 ;       //not existing account id
        presenter.setAccountLoggedIn(-1);
        Assert.assertTrue(presenter.accountDeleted());
    }

    /**
     * Checks that an existing account can be found in Database
     */
    @Test
    public void accountExists(){
        presenter.setAccountLoggedIn(new AccountDAO().findAccount("jkalo@gmail.com").getId());
        Assert.assertFalse(presenter.accountDeleted());
    }

    /**
     * Checks that the account ID is being set properly
     */
    @Test
    public void getCurrentAccount(){
        presenter.setAccountLoggedIn(0);
        Assert.assertEquals(0, presenter.getAccountID());
    }
}
