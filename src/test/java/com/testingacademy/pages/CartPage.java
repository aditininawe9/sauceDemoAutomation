package com.testingacademy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    By cart = By.id("shopping_cart_container");
    By checkout = By.id("checkout");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By code = By.name("postalCode");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cart).click();
    }

    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement checkoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(checkout)
        );
        checkoutBtn.click();
    }

    public void enterFirstName(String fname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))
        );

        firstNameField.findElement(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))
        );

        lastNameField.findElement(lastName).sendKeys(lname);
    }

    public void enterPostalCode(String postalCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement codeField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))
        );

        codeField.findElement(code).sendKeys(postalCode);
    }
}
