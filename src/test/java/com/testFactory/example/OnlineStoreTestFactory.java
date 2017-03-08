package com.testFactory.example;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bozgad on 2/16/17.
 */
@Test(groups = "run")
public class OnlineStoreTestFactory {

    private static List<String> listOfDomains= new ArrayList<String>();
    private static List<String> listOfPages= new ArrayList<String>();
    static {
        listOfDomains.add("url .com");
        listOfDomains.add("url .ro");
        listOfDomains.add("url .uk");
        listOfPages.add("Store section");
        listOfPages.add("Management Section");
        listOfPages.add("Admin Section");
    }

    @Factory()
    public Object[] factoryMethod() {
        return new Object[]{
                new OnlineStoreSanityTests(generateAccountForTestOne(),listOfDomains,listOfPages),
                new OnlineStoreSanityTests(generateAccountForTestTwo(),listOfDomains,listOfPages),
        };
    }

    public Account generateAccountForTestOne() {
        Account account = new Account();
        account.setUsername("Regular User");
        account.setLanguage("English");
        account.setAccessLevel("Store");
        return account;
    }

    public Account generateAccountForTestTwo() {
        Account account = new Account();
        account.setUsername("Admin User");
        account.setLanguage("Spanish");
        account.setAccessLevel("Admin");
        return account;
    }


}
