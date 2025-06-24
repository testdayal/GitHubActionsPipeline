package org.qa.framework;

import static org.qa.framework.BaseFramework.prop;

public class AppConfigurationData {

    public String browser=System.getProperty("browser","chrome");
    public String env=System.getProperty("env","STAGE_QA");
    public String runMode=System.getProperty("runMode","local");
    

}
