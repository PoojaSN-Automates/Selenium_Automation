package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Testbase;

public class Utility extends Testbase{

	public static long time=30;

	public static void explicitWait(By locator,WebDriver driver) {

		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
	public static List<WebElement> waitforElements(By locator, WebDriver driver){
		
		WebDriverWait expWait= new WebDriverWait(driver,Duration.ofSeconds(time));
		
		return expWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public boolean isAlertPresent() {

	    try {

	        ((WebDriver) driver).switchTo().alert();

	        return true;

	    } catch (Exception e) {

	        return false;
	    }
	}
	
	
}
