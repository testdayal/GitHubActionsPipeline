package org.qa.testclass;

import org.testng.annotations.Test;

import static org.qa.framework.WebDriverManager.getDriver;

public class PaymentTest extends BaseTest{

    @Test
    public void pageNavigation() {
          getDriver().get(environmentConfiguration.getUrl());
    }
}
