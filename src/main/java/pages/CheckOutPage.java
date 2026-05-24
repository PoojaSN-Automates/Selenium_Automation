package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;


public class CheckOutPage extends BasePage{

	WebDriver driver;

	private By checkout= By.id("checkout");
	private By firstname= By.id("first-name");
	private By lastname= By.id("last-name");
	private By postalcode= By.id("postal-code");
	private By continuebtn= By.id("continue");
	private By finishbtn= By.id("finish");
	private By message= By.xpath("//h2[contains(.,'Thank you for your order!')]");
	private By cancel= By.id("cancel");
	private By continueshopping= By.id("continue-shopping");
	private By invalidmessage= By.xpath("//h3[contains(.,'Error: Last Name is required')]");
	
	
	public CheckOutPage(WebDriver driver) {
		
		super(driver);

		this.driver= driver;
	}
	
	public void clickOnCheckOut() {

        click(checkout);
    }



    public void enterDetails(
            String firtName,
            String lastName,
            String postalCode) {

        type(firstname, firtName);

        type(lastname, lastName);

        type(postalcode, postalCode);
    }



    public void clickOnContinue() {

        click(continuebtn);
    }



    public void clickOnFinish() {

        click(finishbtn);
    }



    public String verifyMessage() {

        return getText(message);
    }



    public void cancelCheckOut() {

        click(cancel);
    }



    public void clickonContinueShopping() {

        click(continueshopping);
    }



    public String invalidMessage() {

        return getText(invalidmessage);
    }
}
