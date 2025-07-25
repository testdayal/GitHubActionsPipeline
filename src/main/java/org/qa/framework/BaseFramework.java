package org.qa.framework;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseFramework {
    public static Properties prop = new Properties();
    final static String fileLocation = "src/main/java/org/qa/framework/environmentProperties";
    public static Dotenv dotenv;
    public static String runMode;
    public static String envName = null;
    public static WebDriverManager webdriverManager = new WebDriverManager();
    public static AppConfigurationData appConfigurationData = new AppConfigurationData();
    public static EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration();
    public static String browserName;
    public static String runModeConfig;
    public static String envNameConfig;

    public static WebDriverManager getWebdriverManager() {
        return webdriverManager;
    }

    public static void setWebdriverManager(WebDriverManager webdriverManager) {
        BaseFramework.webdriverManager = webdriverManager;
    }

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

    @BeforeSuite
    public void initializeBrowser() throws IOException {

        envFilesConfiguration();
        //setEnvName();
        setEnvNameFramework();
        //setEnvData();
        setEnvDataFramework();
        runMode = appConfigurationData.runMode;
        System.out.println("-----------Initialize-Browser----------");
        getWebdriverManager().getBrowserConfiguration(new AppConfigurationData().browser);
        System.out.println("BrowserName : " + new AppConfigurationData().browser);
        System.out.println("-----------Initialize-Browser----------");
    }


    public void setEnvDataFramework() throws FileNotFoundException {
        envName = environmentConfiguration.setEnvName(appConfigurationData.env);
        if (envName.equalsIgnoreCase("STAGE_QA")) {
            environmentConfiguration.setUrl(dotenv.get("STAGE_QA"));
        } else if (envName.equalsIgnoreCase("DEV_QA")) {
            environmentConfiguration.setUrl(dotenv.get("DEV_QA"));
        } else if (envName.equalsIgnoreCase("PROD_QA")) {
            environmentConfiguration.setUrl(dotenv.get("PROD_QA"));

        } else if (envName.equalsIgnoreCase("TEST_QA")) {
            environmentConfiguration.setUrl(dotenv.get("TEST_QA"));
        }
    }

    public String setEnvNameFramework() throws FileNotFoundException {
        readPropertiesFileForEnv();
        envName = appConfigurationData.env;
        return envName;
    }

    public void envFilesConfiguration() {
        dotenv = Dotenv.configure().directory("src/main/java/resources/.env").load();

    }

    public Properties readPropertiesFileForEnv() {
        InputStream input = null;
        try {
            input = new FileInputStream(fileLocation);
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
