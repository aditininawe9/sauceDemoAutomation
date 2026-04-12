package com.testingacademy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    By inventoryContainer = By.id("contents_wrapper");
    By filterDropDown = By.className("product_sort_container");
    By productName = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");

    public boolean isInventoryPageLoaded() {
       return driver.findElement(inventoryContainer).isDisplayed();
    }

    public void selectFilter(String value) {
        Select select = new Select(driver.findElement(filterDropDown));
        select.selectByValue(value);
    }

    public List<String> getProductNames() {
        List<WebElement> items = driver.findElements(productName);
        List<String> names = new ArrayList<>();
        for(WebElement item : items) {
            names.add(item.getText());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrice);
        List<Double> prices = new ArrayList<>();
        for(WebElement price : priceElements) {
            prices.add(Double.parseDouble(price.getText().replace("$","")));
        }
        return prices;
    }

    public void clickAddToCart(String productName) {
        String productId = productName.toLowerCase().replace(" ","-");
        By addToCart = By.id("add-to-cart-"+productId);
        driver.findElement(addToCart).click();
    }

    public void removeFromCart(String productName) {
        String productId = productName.toLowerCase().replace(" ","-");
        By removeFromCart = By.id("remove-"+productId);
        driver.findElement(removeFromCart).click();
    }

    public void openProduct(String productName) {
        By product = By.xpath("//div[text()='"+productName+"']");
        driver.findElement(product).click();
    }

}
