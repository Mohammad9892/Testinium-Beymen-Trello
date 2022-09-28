package com.Beymen.Locators;

import org.openqa.selenium.By;

public class CartLocators {

    public static By orderSummaryTitle = By.xpath("//*[@class='m-orderSummary__title']");
    public static By productSalePrice = By.xpath("//*[@class='m-productPrice__salePrice']");
    public static By quantityDropdown = By.xpath("//*[@id='quantitySelect0-key-0']");
    public static By removeCartItemBtn = By.xpath("//*[@id='removeCartItemBtn0-key-0']");
    public static By removeCartNotifTitle = By.xpath("//*[@id='notifyTitle']");
    public static By emptyMessageBtn = By.xpath("//*[@class='m-empty__messageBtn']");

}