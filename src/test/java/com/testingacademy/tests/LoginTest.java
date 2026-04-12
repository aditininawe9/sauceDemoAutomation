package com.testingacademy.tests;

import com.testingacademy.base.Base;
import com.testingacademy.pages.InventoryPage;
import com.testingacademy.pages.LoginPage;
import com.testingacademy.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginTest extends Base {
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
    }
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sau");
        Assert.assertTrue(loginPage.getFailureMessage().contains("Username and password do not match any user in this service"));
    }

    @Test
    public void testFilterAZ() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<String> originalString = inventoryPage.getProductNames();
        inventoryPage.selectFilter("az");
        List<String> sortedList = inventoryPage.getProductNames();
        List<String>  expectedList = new ArrayList<>(originalString);
        Collections.sort(expectedList);
        Assert.assertEquals(expectedList, sortedList);
    }

    @Test
    public void testFilterZA() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<String> originalString = inventoryPage.getProductNames();
        inventoryPage.selectFilter("za");
        List<String> sortedList = inventoryPage.getProductNames();
        List<String>  expectedList = new ArrayList<>(originalString);
        Collections.sort(expectedList,Collections.reverseOrder());
        Assert.assertEquals(expectedList, sortedList);
    }

    @Test
    public void testLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<Double> originalString = inventoryPage.getProductPrices();
        inventoryPage.selectFilter("lohi");
        List<Double> sortedList = inventoryPage.getProductPrices();
        List<Double> expectedList = new ArrayList<>(originalString);
        Collections.sort(expectedList);
        Assert.assertEquals(expectedList, sortedList);
    }

    @Test
    public void testHighToLow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<Double> originalString = inventoryPage.getProductPrices();
        inventoryPage.selectFilter("hilo");
        List<Double> sortedList = inventoryPage.getProductPrices();
        List<Double> expectedList = new ArrayList<>(originalString);
        Collections.sort(expectedList,Collections.reverseOrder());
        Assert.assertEquals(expectedList, sortedList);
    }

    @Test
    public void testAddToCartAndRemoveFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickAddToCart("Sauce Labs Backpack");

        inventoryPage.removeFromCart("Sauce Labs Backpack");
    }

    @Test
    public void openDetailPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openProduct("Sauce Labs Backpack");

        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(productPage.getProductTitle(), "Sauce Labs Backpack");
    }
}
