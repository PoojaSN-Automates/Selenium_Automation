package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class Testbase {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    ConfigReader config;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {

    	System.out.println("===== SETUP STARTED =====");
    	
        config = new ConfigReader();

        String browser = System.getProperty("browser", config.getBrowser());
        System.out.println("Browser = " + browser);
        
        String url = config.getUrl();

        WebDriver localDriver = null;

        //  CHROME 
        if (browser.equalsIgnoreCase("chrome")) {

        	 System.out.println("Creating ChromeDriver...");
        	 WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--guest");

            // CI STABLE HEADLESS OPTIONS
            if (config.isHeadless()) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
            }

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.credit_card_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            localDriver = new ChromeDriver(options);
            
            System.out.println("Chrome Driver Created Successfully");
        }

        // FIREFOX
        else if (browser.equalsIgnoreCase("firefox")) {

        	WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (config.isHeadless()) {
                options.addArguments("--headless");
            }

            localDriver = new FirefoxDriver(options);
            System.out.println("Firefox Driver Created Successfully");
        }

        //  EDGE
        else if (browser.equalsIgnoreCase("edge")) {

        	WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();

            if (config.isHeadless()) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            localDriver = new EdgeDriver(options);
            System.out.println("Edge Driver Created Successfully");
        }

        else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.set(localDriver);
        
        System.out.println("Driver stored in ThreadLocal");

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(config.getTimeout()));

        System.out.println("Implicit wait set");
        
        getDriver().get(url);
        
        System.out.println("URL opened successfully");
        System.out.println("===== SETUP COMPLETED =====");
    }

    @AfterMethod
    public void tearDown() {
    	
    	  System.out.println("===== TEARDOWN STARTED =====");

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            
            System.out.println("Driver closed successfully");
        }
        
        System.out.println("===== TEARDOWN COMPLETED =====");
    }
    
}