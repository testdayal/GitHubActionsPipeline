package org.qa.testclass;

import io.github.cdimascio.dotenv.Dotenv;
import org.qa.framework.AppConfigurationData;
import org.qa.framework.BaseFramework;
import org.qa.framework.EnvironmentConfiguration;
import org.qa.framework.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.util.Properties;

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


}