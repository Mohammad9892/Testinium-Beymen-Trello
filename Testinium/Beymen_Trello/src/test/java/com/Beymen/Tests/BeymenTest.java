package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import com.Beymen.Pages.CartPage;
import com.Beymen.Pages.ProductDetailPage;
import com.Beymen.Pages.ProductListPage;
import org.junit.Test;

import java.io.IOException;

public class BeymenTest extends BaseTest {

    @Test
    public void beymenTest() throws IOException {
        logger = report.createTest("Beymen Selenium Web Automation");

        BasePage basePage = new BasePage();
        ProductListPage productListPage = new ProductListPage();
        ProductDetailPage productDetailPage = new ProductDetailPage();
        CartPage cartPage = new CartPage();

        logInfo("The www.beymen.com site is opened.");
        basePage.openPage();

        logInfo("Check that the main page is opened.");
        basePage.checkPage();

        logInfo("The word “shorts” is entered into the search box.");
        basePage.searchInInput(0, "shorts");

        logInfo("The word “shorts” entered in the search box is deleted.");
        basePage.clearSearch();

        logInfo("The word “shirt” is entered into the search box.");
        basePage.searchInInput(1, "shirt");

        logInfo("Press the \"enter\" key on the keyboard.");
        basePage.pressEnter();

        logInfo("A random product is selected from the exhibited products according to the result.");
        productListPage.getRandomProduct();

        logInfo("The product information and amount information of the selected product are written to the txt file.");
        productDetailPage.writeProductInformation();

        logInfo("The selected product is added to the cart.");
        productDetailPage.addToBasket();

        logInfo("The accuracy of the price on the product page and the price of the product in the basket is compared.");
        productDetailPage.checkPriceInBasket();

        logInfo("It is verified that the number of products is 2 by increasing the number.");
        cartPage.increaseQuantity("2");

        logInfo("The product is deleted from the basket and the basket is checked to be empty.");
        cartPage.deleteFromBasket();

    }

    public void logInfo(String text) {
        logger.info(text);
        log4j.info(text);
    }

}