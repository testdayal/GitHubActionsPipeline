package org.qa.testclass;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.framework.AppConfigurationData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.qa.framework.WebDriverManager.getDriver;

public class PaymentTest extends BaseTest {

    @Test
    public void loginTest() {
        WebDriverWait wait=new WebDriverWait(getDriver(),Duration.ofSeconds(30));
        getDriver().get(environmentConfiguration.getUrl());
        getDriver().manage().timeouts().getPageLoadTimeout();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        String userNameInput=new AppConfigurationData().loginUserName;
        String passwordInput=new AppConfigurationData().loginPassword;
        System.out.println("--------Credentials-Data-Validation-----------");
        System.out.println("UserName-Entered : "+userNameInput);
        System.out.println("Password-Entered : "+passwordInput);
        System.out.println("--------Credentials-Data-Validation-----------");
        getDriver().findElement(By.name("username")).sendKeys(userNameInput);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        getDriver().findElement(By.name("password")).sendKeys(passwordInput);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        clickElement(getDriver().findElement(By.xpath("//button[@type='submit']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[text()=' Logout']")));
        String validationLoginMessage=getDriver().findElement(By.xpath("//h4[contains(text(),'Welcome to the Secure Area. When you are done click logout below.')]")).getText();
        System.out.println("--------Post-Login-Data-Validation-----------");
        System.out.println("Post-Login-Message : "+ validationLoginMessage );
        Assert.assertTrue(validationLoginMessage.contains("Welcome to the Secure Area. When you are done click logout below."));
        System.out.println("--------Post-Login-Data-Validation-----------");
    }
}
