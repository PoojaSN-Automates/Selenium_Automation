package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties prop;
	
	public ConfigReader()
	{
		try {
			
			FileInputStream fis= new FileInputStream("src/main/java/resources/config.properties");
			prop= new Properties();
			prop.load(fis);
		} catch (IOException e) {
			 throw new RuntimeException("Config file not found");
		}
	}
	
	public String getBrowser() {
		return prop.getProperty("browser");
	}
	
	/*public String getUrl() {
		return prop.getProperty("url");
	}*/
	
	public String getUsername() {

	    return prop.getProperty(
	            "username");
	}

	public String getPassword() {

	    return prop.getProperty(
	            "password");
	}

	public long getTimeout() {

	    return Long.parseLong(
	            prop.getProperty(
	                    "timeout"));
	}

	public boolean isHeadless() {

	    return Boolean.parseBoolean(
	            prop.getProperty(
	                    "headless"));
	}
	
	//For multiple environments
	public String getUrl() {
		String env = System.getProperty("environment", prop.getProperty("environment"));

        switch (env.toLowerCase()) {

            case "qa":
                return prop.getProperty("qa.url");

            case "uat":
                return prop.getProperty("uat.url");

            case "prod":
                return prop.getProperty("prod.url");

            default:
                throw new RuntimeException("Invalid environment: " + env);
        }
    }
	
}
