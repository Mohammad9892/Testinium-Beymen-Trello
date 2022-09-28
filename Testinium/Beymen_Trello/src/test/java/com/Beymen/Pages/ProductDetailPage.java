package com.Beymen.Pages;

import com.Beymen.Locators.CartLocators;
import com.Beymen.Locators.ProductDetailLocators;
import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.ElementHelper;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;

public class ProductDetailPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;

    private String detailPrice;

    public ProductDetailPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
    }

    public void writeProductInformation() throws IOException {
        try {
            elementHelper.writeToTxt(ProductDetailLocators.productDescription, ProductDetailLocators.productColor, ProductDetailLocators.productPrice);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void addToBasket() throws IOException {
        try {
            detailPrice = elementHelper.getText(ProductDetailLocators.productPrice);
            int bodySizeCount = elementHelper.findElements(ProductDetailLocators.productSizes).size();
            while (true) {
                Random random = new Random();
                int count = random.nextInt(bodySizeCount);
                WebElement selectedBody = elementHelper.findElements(ProductDetailLocators.productSizes).get(count);
                if (!selectedBody.getAttribute("class").contains("-disabled")) {
                    selectedBody.click();
                    break;
                }
            }
            elementHelper.click(ProductDetailLocators.addBasketBtn);
            elementHelper.checkElementVisible(ProductDetailLocators.addedToCartNotifTitle);
            String actualText = elementHelper.getText(ProductDetailLocators.addedToCartNotifTitle);
            String expectedText = "Added to Cart";
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

    public void checkPriceInBasket() throws IOException {
        try {
            elementHelper.click(ProductDetailLocators.addedToCartNotifBtn);
            elementHelper.checkElementVisible(CartLocators.orderSummaryTitle);
            String salePrice = elementHelper.getText(CartLocators.productSalePrice);
            if (detailPrice.equals(salePrice)) {
                Assert.assertEquals(detailPrice, salePrice);
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