package org.qa.framework;

public class EnvironmentConfiguration {

    private String envName;
    private String url;

    public String getEnvName() {
        return envName;
    }

    public String setEnvName(String envName) {
        return this.envName = envName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
