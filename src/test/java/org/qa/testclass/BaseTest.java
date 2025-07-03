package org.qa.testclass;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.qa.framework.AppConfigurationData;
import org.qa.framework.BaseFramework;
import org.qa.framework.EnvironmentConfiguration;
import org.qa.framework.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.function.Function;

import static org.qa.framework.WebDriverManager.getDriver;

public class BaseTest extends BaseFramework {

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        try {
            if (getWebdriverManager().getDriver() != null) {
                getWebdriverManager().getDriver().quit();
            } else {
                getWebdriverManager().getDriver().close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickElement(WebElement element){
        JavascriptExecutor jse=(JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click();",element);
    }


}