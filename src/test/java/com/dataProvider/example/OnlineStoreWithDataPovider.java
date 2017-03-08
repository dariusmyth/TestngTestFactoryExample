package com.dataProvider.example;

import com.testFactory.example.Account;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by bozgad on 3/3/17.
 */
public class OnlineStoreWithDataPovider {

    private Account regularAccount;
    private Account adminAccount;

    @BeforeClass()
    public void beforeClass() {
        System.out.println(" ----------------------------------------------------------------");
        regularAccount = new Account();
        regularAccount.setUsername("Regular User");
        regularAccount.setLanguage("English");
        regularAccount.setAccessLevel("Store");

        adminAccount = new Account();
        adminAccount.setUsername("Admin User");
        adminAccount.setLanguage("Spanish");
        adminAccount.setAccessLevel("Admin");
        System.out.println("Before SimpleTest class executed for account");
        System.out.println(" ----------------------------------------------------------------");

    }

    @AfterClass()
    public void afterClass(){
        System.out.println(" ----------------------------------------------------------------");
        System.out.println("After SimpleTest class executed for account ");
        System.out.println(" ----------------------------------------------------------------");
    }

    @DataProvider(name = "getLoginDetails")
    public Object[][] getLoginDetails(){

        return new Object[][]{
                {"url .com",regularAccount},
                {"url .ro",regularAccount},
                {"url .uk",regularAccount},
                {"url .com",adminAccount},
                {"url .ro",adminAccount},
                {"url .uk",adminAccount},
        };
    }

    @DataProvider(name = "getPermissionDetails")
    public Object[][] getPermissions(){

        return new Object[][]{
                {"Store section",regularAccount},
                {"Management Section",regularAccount},
                {"Admin section",regularAccount},
                {"Store section",adminAccount},
                {"Management Section",adminAccount},
                {"Admin section",adminAccount},
        };
    }

    @DataProvider(name = "purchaseItemDetails")
    public Object[][] getPurchaseItemDetails(){

        return new Object[][]{
                {"Phone",regularAccount},
                {"Sink",regularAccount},
                {"Phone",adminAccount},
                {"Sink",adminAccount},
        };
    }
    @DataProvider(name = "getLocalization")
    public Object[][] getLocalizationDetails(){
        return new Object[][]{
                {regularAccount},
                {adminAccount},
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
        System.out.println("Purhcasing item: "+isUserAllowedToPurchaseFromStore(account));
        System.out.println("Purchassing item: "+item);
    }

    @Test(dataProvider = "getLocalization")
    public void verifyStoreLocalization(Account account) {
        System.out.println("Current thread: "+ Thread.currentThread());
        System.out.println("Login with user "+account.getUsername());
        System.out.println("Verify section is in correct language " +account.getLanguage());

    }

    @Test(dataProvider = "getPermissionDetails")
    public void verifyUserPermissionsToSection(String section, Account account) {
        System.out.println("Current thread: "+ Thread.currentThread());
        System.out.println("Login with user "+account.getUsername());
        System.out.println("Access section "  + isUserAllowedToAccessSection(section,account));

    }

    @Test()
    public void verifyItemIsDisplayedOnStore(){
        System.out.println("Verify desktop is displayed in store");
    }

    private boolean isUserAllowedToAccessSection(String section, Account account){
        return section.contains(account.getAccessLevel());
    }
    private boolean isUserAllowedToPurchaseFromStore( Account account){
        return !account.getAccessLevel().equals("Admin");
    }
}
