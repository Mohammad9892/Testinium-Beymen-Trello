package com.Beymen.Pages;

import com.Beymen.Locators.HomeLocators;
import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.DriverManager;
import com.Beymen.Utilities.ElementHelper;
import com.Beymen.Utilities.ReadExcelFile;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;

    public BasePage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
    }

    public void openPage() throws IOException {
        try {
            DriverManager.initializeDriver();
            elementHelper.checkElementVisible(HomeLocators.onetrustAcceptBtn);
            elementHelper.checkElementClickable(HomeLocators.onetrustAcceptBtn);
            elementHelper.click(HomeLocators.onetrustAcceptBtn);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void checkPage() throws IOException {
        try {
            elementHelper.checkElementVisible(HomeLocators.headerLogo);
            String actualTitle = elementHelper.getAttribute(HomeLocators.headerLogo, "title");
            String expectedTitle = "Beymen";
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

    public void searchInInput(int column, String expectedText) throws IOException {
        try {
            elementHelper.sendKey(HomeLocators.searchInput, ReadExcelFile.readFromExcel(column));
            String actualText = elementHelper.getAttribute(HomeLocators.searchInput, "value");
            if (expectedText.equals(actualText)) {
                Assert.assertEquals(expectedText, actualText);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void clearSearch() throws IOException {
        try {
            elementHelper.sendKey(HomeLocators.searchInput, Keys.CONTROL + "A" + Keys.DELETE);
            String actualText = elementHelper.getAttribute(HomeLocators.searchInput, "value");
            String expectedText = "";
            if (expectedText.equals(actualText)) {
                Assert.assertEquals(expectedText, actualText);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void pressEnter() throws IOException {
        try {
            elementHelper.sendKey(HomeLocators.searchInput, "" + Keys.ENTER);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

}