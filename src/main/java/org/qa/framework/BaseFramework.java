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
    public static  Properties prop = new Properties();
    final static String fileLocation="src/main/java/org/qa/framework/environmentProperties";
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
        System.out.println("Initialize-Browser");
        getWebdriverManager().getBrowserConfiguration("chrome");
    }

//    public void setEnvData() throws FileNotFoundException {
//        String URL = null;
//        if (runMode.equalsIgnoreCase("local")) {
//            if (envName.equalsIgnoreCase("STAGE_QA")) {
//                environmentConfiguration.setUrl( dotenv.get("STAGE_QA"));
//            } else if (envName.equalsIgnoreCase("DEV_QA")) {
//                environmentConfiguration.setUrl(dotenv.get("DEV_QA"));
//
//            } else if (envName.equalsIgnoreCase("PROD_QA")) {
//                environmentConfiguration.setUrl(dotenv.get("PROD_QA"));
//
//
//            } else if (appConfigurationData.runMode.equalsIgnoreCase("CI")) {
//                envName = environmentConfiguration.setEnvName(appConfigurationData.env);
//                if (envName.equalsIgnoreCase("STAGE_QA")) {
//                    environmentConfiguration.setUrl(dotenv.get("STAGE_QA"));
//                } else if (envName.equalsIgnoreCase("DEV_QA")) {
//                    environmentConfiguration.setUrl(dotenv.get("DEV_QA"));
//                } else if (envName.equalsIgnoreCase("PROD_QA")) {
//                    environmentConfiguration.setUrl(dotenv.get("PROD_QA"));
//
//                }
//            }
//        }
//    }

    public void setEnvDataFramework() throws FileNotFoundException {
        envName = environmentConfiguration.setEnvName(appConfigurationData.env);
        if (envName.equalsIgnoreCase("STAGE_QA")) {
            environmentConfiguration.setUrl(dotenv.get("STAGE_QA"));
        } else if (envName.equalsIgnoreCase("DEV_QA")) {
            environmentConfiguration.setUrl(dotenv.get("DEV_QA"));
        } else if (envName.equalsIgnoreCase("PROD_QA")) {
            environmentConfiguration.setUrl(dotenv.get("PROD_QA"));

        }
    }

    //    public String setEnvName() throws FileNotFoundException {
//        if (runMode.equalsIgnoreCase("local")) {
//            Properties prop = new Properties();
//            InputStream input = null;
//            try {
//                input = new FileInputStream(fileLocation);
//                prop.load(input);
//                envName = prop.getProperty("envName");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (runMode.equalsIgnoreCase("CI")) {
//            envName = appConfigurationData.env;
//        }
//        return envName;
//    }
    public String setEnvNameFramework() throws FileNotFoundException {
        readPropertiesFileForEnv();
        envName = appConfigurationData.env;
        return  envName;
    }

    public void envFilesConfiguration(){
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
