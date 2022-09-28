package com.Beymen.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    static WebDriver driver;
    static Properties properties;

    public static WebDriver initializeDriver() {
        if (driver == null) {
            properties = ConfigurationReader.getProperties();

            String browser = properties.getProperty("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }

            String url = properties.getProperty("url");
            int impWait = Integer.parseInt(properties.getProperty("implicityWait"));
            int pageWait = Integer.parseInt(properties.getProperty("pageLoadTimeout"));
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(impWait, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(pageWait, TimeUnit.SECONDS);
        }

        return getDriver();
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void terminateDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}