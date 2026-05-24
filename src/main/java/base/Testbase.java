package base;


import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;


//Initialize WebDriver and launch browser here
public class Testbase {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	ConfigReader config;
	
	public static WebDriver getDriver() {
        return driver.get();
    }
	
	@BeforeMethod
	public void setUp() {
		
		config= new ConfigReader();
		
		String browser = config.getBrowser();
        String url = config.getUrl();
        
        if(browser.equalsIgnoreCase("chrome")) {
        
		ChromeOptions options= new ChromeOptions();// these are commonly used adding capabilities for chrome browser to avoid any issues on browser set up
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--guest");

Map<String, Object> prefs =
        new HashMap<>();

prefs.put(
        "credentials_enable_service",
        false);

prefs.put(
        "profile.password_manager_enabled",
        false);

prefs.put(
        "autofill.profile_enabled",
        false);

prefs.put(
        "autofill.credit_card_enabled",
        false);

options.setExperimentalOption(
        "prefs",
        prefs);
		
		
		driver.set(new ChromeDriver(options));
		
        }
        getDriver().get(url);
	}
	
	@AfterMethod
	public void tearDown() {
		if(getDriver()!=null) {
			getDriver().quit();
			 driver.remove(); //(prevents memory leak)
		}
	}
}
