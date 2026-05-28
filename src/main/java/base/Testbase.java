package base;


import java.time.Duration;
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

	 // ThreadLocal Driver
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	// Config object
	ConfigReader config;
	
	  // Get Driver
	public static WebDriver getDriver() {
        return driver.get();
    }
	
	@BeforeMethod
	public void setUp() {
		
// Initialize config
		config= new ConfigReader();
		
		String browser = config.getBrowser();
        String url = config.getUrl();
        
        // Chrome Browser
        if(browser.equalsIgnoreCase("chrome")) {
       
 // Browser options
		ChromeOptions options= new ChromeOptions();// these are commonly used adding capabilities for chrome browser to avoid any issues on browser set up
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--guest");
		
		  // Headless support
        if(config.isHeadless()) {

            options.addArguments(
                    "--headless=new");
        }
		
// Disable password manager
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
		
//Initialize driver
		driver.set(new ChromeDriver(options));
		
        }
        // Invalid browser
        else {

            throw new RuntimeException(
                    "Browser not supported");
        }
        
     // Maximize browser
        getDriver().manage()
        .window().maximize();



        // Implicit wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getTimeout()));
        
     // Open URL
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
