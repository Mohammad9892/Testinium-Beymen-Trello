package com.Beymen.Utilities;

import com.Beymen.Tests.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.Beymen.Tests.BaseTest.log4j;

public class ElementHelper {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    ExtentTest logger;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.action = new Actions(driver);
        this.logger = BaseTest.logger;
    }

    public WebElement findElement(By key) {
        WebElement element = presenceElement(key);
        scrollToElement(element);
        return element;
    }

    public List<WebElement> findElements(By key) {
        List<WebElement> elements = presenceElements(key);
        //scrollToElement(elements.get(0));
        return elements;
    }

    public void click(By key) {
        findElement(key).click();
    }

    public void sendKey(By key, String text) {
        findElement(key).sendKeys(text);
    }

    public String getText(By key) {
        return findElement(key).getText();
    }

    public void checkElementVisible(By key) {
        wait.until(ExpectedConditions.visibilityOf(findElement(key)));
    }

    public void checkElementClickable(By key) {
        wait.until(ExpectedConditions.elementToBeClickable(findElement(key)));
    }

    public String getAttribute(By key, String attr) {
        return findElement(key).getAttribute(attr);
    }

    public WebElement presenceElement(By key) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }

    public List<WebElement> presenceElements(By key) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(key));
    }

    public void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void writeToTxt(By descriptionKey, By colorKey, By priceKey) {
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            FileWriter fileWriter = new FileWriter("./Reports/productInfos-" + dateName + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Product description : " + getText(descriptionKey));
            printWriter.println("Product color : " + getText(colorKey));
            printWriter.println("Product price : " + getText(priceKey));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testCaseFailed() throws IOException {
        String screenshotPath = Screenshoot.takeScreenshot(driver);
        logger.log(Status.FAIL, MarkupHelper.createLabel("Test Case Step FAILED", ExtentColor.RED));
        logger.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        log4j.info("Test Case Step FAILED");
    }

    public void testCasePassed() {
        logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Step PASSED", ExtentColor.GREEN));
        log4j.info("Test Case Step PASSED");
    }



}