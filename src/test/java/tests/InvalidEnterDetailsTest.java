package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.CheckOutPage;
import pages.HomePage;
import pages.LoginPage;

public class InvalidEnterDetailsTest extends Testbase {
	
	@Test
	
	public void verifyInvalidetails() {

	LoginPage login= new LoginPage(Testbase.getDriver());
	
	try {
		HomePage home= login.verifyLogin("standard_user", "secret_sauce");
		

		String [] items= {"Sauce Labs Backpack",
				"Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt"
				};
		
		home.addMultipleProducts(items);
		Assert.assertEquals(home.getCartCount(), "3");
		home.clickOncart();
		
		CheckOutPage checkout= new CheckOutPage(getDriver());
		checkout.clickOnCheckOut();
		checkout.enterDetails(
				"Jiya",
				"",
				"234566");
		
		checkout.clickOnContinue();
		Assert.assertEquals(checkout.invalidMessage(), "Error: Last Name is required"); 
		System.out.println("Invalid details verified");
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
