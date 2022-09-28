package com.Beymen.Tests;

import com.Beymen.Utilities.ConfigurationReader;
import com.Beymen.Utilities.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    Properties properties;
    ExtentReports report;
    public static ExtentTest logger;
    ExtentSparkReporter spark;

    public final static Logger log4j = Logger.getLogger(BeymenTest.class);

    @Before
    public void before() {
        log4j.info("Test started.");
        properties = ConfigurationReader.initializeProperties();
        driver = DriverManager.initializeDriver();
        report = new ExtentReports();
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/ExtentReport.html";
        spark = new ExtentSparkReporter(path);
        report.attachReporter(spark);
        report.setSystemInfo("Environment", "Test");
        report.setSystemInfo("User Name", "Mohammad Umer Baig");
        report.setSystemInfo("BrowserType", ConfigurationReader.getProperties().getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        spark.config().setDocumentTitle(ConfigurationReader.getProperties().getProperty("documentTitle"));
        spark.config().setReportName(ConfigurationReader.getProperties().getProperty("reportName"));
    }

    @After
    public void after() {
        DriverManager.terminateDriver();
        report.flush();
        log4j.info("Test DONE.");
    }

}