package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.CheckOutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;
import utils.ConfigReader;

public class CompleteOrderFlow extends Testbase{
	
	 ConfigReader config = new ConfigReader();


	@Test
public void verifyCompleteOrder() {
		
		LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
			
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
					"Ray",
					"234566");
			
			checkout.clickOnContinue();
			checkout.clickOnFinish();
			Assert.assertEquals(checkout.verifyMessage(), "Thank you for your order!"); 
			System.out.println("Checkout Completed");
			
			MenuPage menu= new MenuPage(getDriver());
			Thread.sleep(2000);
			menu.clickOnMenu();
			menu.clickonLogout();
			
			Assert.assertTrue(getDriver().getCurrentUrl().contains("saucedemo")); 
			System.out.println("Logout Successful");
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
