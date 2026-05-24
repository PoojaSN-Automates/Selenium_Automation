package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

	 private WebDriver driver;

	    private By un = By.id("user-name");

	    private By pwd = By.id("password");

	    private By loginBtn = By.id("login-button");

	    private By errorMsg = By.xpath("//h3[@data-test='error']");



	    public LoginPage(WebDriver driver) {

	        super(driver);

	        this.driver = driver;
	    }


	    public HomePage verifyLogin(String userName,String passWord)throws InterruptedException {

	        type(un, userName);

	        type(pwd, passWord);

	        click(loginBtn);

	        return new HomePage(driver);
	    }



	    public boolean isErrorDisplayed() {

	        try {

	            return isDisplayed(errorMsg);

	        } catch (Exception e) {

	            return false;
	        }
	    }



	    public String getErrorMessage() {

	        try {

	            return getText(errorMsg);

	        } catch (Exception e) {

	            return "";
	        }
	    }
		
	
}
