package org.qa.framework;

import static org.qa.framework.BaseFramework.prop;

public class AppConfigurationData {

    public String browser=System.getProperty("browser","chrome");
    public String env=System.getProperty("env","TEST_QA");
    public String runMode=System.getProperty("runMode","local");
    public String loginUserName=System.getProperty("username","testlogin");
    public String loginPassword=System.getProperty("password","testpassword");


}
