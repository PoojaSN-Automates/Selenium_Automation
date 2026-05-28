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

import utils.ConfigReader;

public class Testbase {

    // ThreadLocal Driver (for parallel execution)
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
        config = new ConfigReader();

        String browser = System.getProperty("browser", config.getBrowser());
        String url = config.getUrl();

        WebDriver localDriver = null;

        // CHROME
       
        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--guest");

            // Headless support
            if (config.isHeadless()) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            // Disable password manager
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.credit_card_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            localDriver = new ChromeDriver(options);
        }

       
        // FIREFOX
       
        else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (config.isHeadless()) {
                options.addArguments("--headless");
            }

            localDriver = new FirefoxDriver(options);
        }

        
        // EDGE
       
        else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            options.addArguments("--start-maximized");

            if (config.isHeadless()) {
                options.addArguments("--headless=new");
            }

            localDriver = new EdgeDriver(options);
        }

    
        // INVALID BROWSER
       
        else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        // Set driver in ThreadLocal
        driver.set(localDriver);

        // Common settings
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(config.getTimeout()));

        getDriver().manage().window().maximize();

        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // prevents memory leak in parallel runs
        }
    }
}
