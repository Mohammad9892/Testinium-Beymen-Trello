package com.Beymen.Locators;

import org.openqa.selenium.By;

public class ProductDetailLocators {

    public static By productDescription = By.xpath("//*[@class='o-productDetail__description']");
    public static By productColor = By.xpath("//*[@class='m-colorsSlider__top']//label");
    public static By productPrice = By.xpath("//*[@id='priceNew']");
    public static By productSizes = By.xpath("//*[contains(@class, 'm-variation__item')]");
    public static By addBasketBtn = By.xpath("//*[@id='addBasket']");
    public static By addedToCartNotifTitle = By.xpath("//*[@class='m-notification__title']");
    public static By addedToCartNotifBtn = By.xpath("//*[@class='m-notification__button btn']");

}