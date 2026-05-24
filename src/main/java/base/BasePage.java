package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {

        this.driver = driver;
    }
	
	// Click
    public void click(By locator) {

        driver.findElement(locator).click();
    }

    
    // Type text
    public void type(By locator,
                     String text) {

        driver.findElement(locator)
              .sendKeys(text);
    }

    
    // Get text
    public String getText(By locator) {

        return driver.findElement(locator)
                     .getText();
    }

    
    // Check displayed
    public boolean isDisplayed(By locator) {

        return driver.findElement(locator)
                     .isDisplayed();
    }

}
