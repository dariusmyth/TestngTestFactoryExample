package com.testFactory.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by bozgad on 2/16/17.
 */
public class OnlineStoreSanityTests{

    private Account account;
    private List<String> listOfDomains;
    private List<String> listOfPages;
    public OnlineStoreSanityTests(Account account, List<String> listOfDomains, List<String> listOfPages) {
        this.account = account;
        this.listOfDomains = listOfDomains;
        this.listOfPages = listOfPages;
    }

    @DataProvider(name = "getLoginDetails")
    public Object[][] getLoginDetails(){
        Object[][] objectArray = new Object[listOfDomains.size()][];
        for (int i = 0; i < listOfDomains.size(); i++) {
            objectArray[i] = new Object[2];
            objectArray[i][0] = listOfDomains.get(i);
            objectArray[i][1] = account;
        }
        return objectArray;
    }

    @DataProvider(name = "getPermissionDetails")
    public Object[][] getPermissions(){
        Object[][] objectArray = new Object[listOfPages.size()][];
        for (int i = 0; i < listOfPages.size(); i++) {
            objectArray[i] = new Object[2];
            objectArray[i][0] = listOfPages.get(i);
            objectArray[i][1] = account;
        }
        return objectArray;
    }

    @DataProvider(name = "purchaseItemDetails")
    public Object[][] getPurchaseItemDetails(){

        return new Object[][]{
                {"Phone",account},
                {"Sink",account},
        };
    }

    @Test(dataProvider = "getLoginDetails")
    public void verifyLogin(String url, Account account) {
        System.out.println("Accessing the url "+url);
        System.out.println("Login with account " + account.getUsername());
        System.out.println("Login performed with success");
    }

    @Test(dataProvider = "purchaseItemDetails")
    public void verifyUserCanPurchaseItems(String item, Account account) {
        System.out.println("Login with username "+ account.getUsername());
        System.out.println("Purhcasing item: "+item);
    }

    @Test()
    public void verifyStoreLocalization() {
        System.out.println("Login with user "+account.getUsername());
        System.out.println("Verify section is in correct language " +account.getLanguage());

    }

    @Test(dataProvider = "getPermissionDetails")
    public void verifyUserPermissionsToSection(String section, Account account) {
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

}
