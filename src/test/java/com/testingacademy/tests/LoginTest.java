package com.testingacademy.tests;

import com.testingacademy.base.Base;
import com.testingacademy.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends Base {
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sau");
        Assert.assertTrue(loginPage.getFailureMessage().contains("Epic sadface: Username and password do not match any user in this service"));
    }
}
