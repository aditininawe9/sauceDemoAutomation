package com.testingacademy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");

    public void enterUsername(String user) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLoginButton();
    }
    public String getFailureMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        );
        return message.getText();
    }
}
