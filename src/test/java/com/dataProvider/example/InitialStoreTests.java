package com.dataProvider.example;

import com.testFactory.example.Account;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by bozgad on 3/3/17.
 */
public class InitialStoreTests {

    private Account regularAccount;

    @BeforeClass()
    public void beforeClass() {
        System.out.println(" ----------------------------------------------------------------");
        regularAccount = new Account();
        regularAccount.setUsername("Regular User");
        regularAccount.setLanguage("English");
        System.out.println("Before SimpleTest class executed for account");
        System.out.println(" ----------------------------------------------------------------");

    }

    @DataProvider(name = "getLoginDetails")
    public Object[][] getLoginDetails(){

        return new Object[][]{
                {"url .com",regularAccount},
                {"url .ro",regularAccount},
                {"url .uk",regularAccount},
        };
    }
    @DataProvider(name = "purchaseItemDetails")
    public Object[][] getPurchaseItemDetails(){

        return new Object[][]{
                {"Phone",regularAccount},
                {"Sink",regularAccount},
        };
    }

    @Test(dataProvider = "getLoginDetails")
    public void verifyLogin(String url, Account account) {
        System.out.println("Current thread: "+ Thread.currentThread());
        System.out.println("Accessing the url "+url);
        System.out.println("Login with account " + account.getUsername());
        System.out.println("Login performed with success");
    }

    @Test(dataProvider = "purchaseItemDetails")
    public void verifyUserCanPurchaseItems(String item, Account account) {
        System.out.println("Current thread: "+ Thread.currentThread());
        System.out.println("Login with username "+ account.getUsername());
        System.out.println("Purchassing item: "+item);
    }

    @Test()
    public void verifyStoreLocalization() {
        System.out.println("Current thread: "+ Thread.currentThread());
        System.out.println("Login with user "+regularAccount.getUsername());
        System.out.println("Verify section is in correct language " +regularAccount.getLanguage().equals("English"));

    }


    @Test()
    public void verifyItemIsDisplayedOnStore(){
        System.out.println("Verify desktop item is displayed in store");
    }

}
