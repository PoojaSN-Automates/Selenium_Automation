package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import utils.Utility;

public class MenuPage extends BasePage {
	
	WebDriver driver;
	
	
	public MenuPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
	}

	// Menu button
    private By menuBtn =By.id("react-burger-menu-btn");

    // All menu items
    private By menuItems = By.xpath("//a[@class='bm-item menu-item']");
    
    private By logout= By.xpath("//a[@id='logout_sidebar_link']");
    
	public void clickOnMenu() {
		
		click(menuBtn);
	}
	
	
	public List<String> getmenuItems() {
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuItems));
		
		List <WebElement> menuitems= driver.findElements(menuItems);
		
		List<String> actualmenu= new ArrayList<>();
		
		for(WebElement allmenuitems:menuitems) {
			
			System.out.println(allmenuitems.getText());
			
			actualmenu.add(allmenuitems.getText());
		}
		return actualmenu;
	}
	
	
	public void clickonLogout() {
		
		Utility.explicitWait(logout, driver);
		click(logout);
	}
	
}
