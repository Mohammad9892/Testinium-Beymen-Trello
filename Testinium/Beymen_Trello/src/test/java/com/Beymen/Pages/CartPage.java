package com.Beymen.Pages;

import com.Beymen.Locators.CartLocators;
import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.ElementHelper;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;

    public CartPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
    }

    public void increaseQuantity(String quantity) throws IOException {
        try {
            Select select = new Select(elementHelper.findElement(CartLocators.quantityDropdown));
            List<WebElement> options = select.getOptions();
            int optionSize = options.size();
            for (int i = 0; i < optionSize; i++) {
                if (options.get(i).getText().contains(quantity)) {
                    select.selectByValue(quantity);
                    break;
                }
            }
            String actualQuantity = select.getFirstSelectedOption().getText();
            if ((quantity + "piece").equals(actualQuantity)) {
                Assert.assertEquals(quantity + "piece", actualQuantity);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void deleteFromBasket() throws IOException {
        try {
            elementHelper.click(CartLocators.removeCartItemBtn);
            elementHelper.checkElementVisible(CartLocators.removeCartNotifTitle);
            elementHelper.checkElementVisible(CartLocators.emptyMessageBtn);
            String actualTitle = elementHelper.getAttribute(CartLocators.emptyMessageBtn, "title");
            String expectedTitle = "Continue Shopping";
            if (expectedTitle.equals(actualTitle)) {
                Assert.assertEquals(expectedTitle, actualTitle);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

}