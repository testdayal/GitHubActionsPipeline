package org.qa.framework;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.html5.AppCacheStatus;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {

    public static WebDriver driver;



    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        WebDriverManager.driver = driver;
    }

    public void getBrowserConfiguration(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions=setupChromeCapabilitiesAndOptions();
            chromeOptions.addArguments("start-maximized");
            driver = new ChromeDriver(chromeOptions);
            setDriver(driver);


        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("start-maximized");
            driver = new FirefoxDriver(firefoxOptions);
            setDriver(driver);
        }

        else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("start-maximized");
            driver = new EdgeDriver(edgeOptions);
            setDriver(driver);
        }
    }

    public static ChromeOptions setupChromeCapabilitiesAndOptions() {
        DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--lang=" + "en-ca");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--incognito");
        //chromeOptions.addArguments("start-fullscreen");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeCapabilities.setCapability(CapabilityType.BROWSER_NAME, new AppConfigurationData().browser);
        chromeCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, "true");
        chromeCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10);
        chromeCapabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("os.version"));
        chromeOptions.merge(chromeCapabilities);
        return chromeOptions;
    }
}

