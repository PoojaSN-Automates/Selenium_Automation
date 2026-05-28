package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.CheckOutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Utility;

public class CheckOutTest extends Testbase{

	Utility util= new Utility();
	 ConfigReader config = new ConfigReader();
	 
	@Test
	public void verifyCheckOut() {
		
		LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
			
			//util.isAlertPresent();
			
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
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//To verify Cancel on CheckOut
	
	@Test
	public void verifyCancelonCheckOut() {
		
LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
			
			//util.isAlertPresent();
			
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
			checkout.cancelCheckOut();
			Assert.assertTrue(home.isProductPageDisplayed());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Too verify Continue shopping 
	
	@Test
	public void verifyContinueShopping() {

	LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
			
			//util.isAlertPresent();
			
			String [] items= {"Sauce Labs Backpack",
					"Sauce Labs Bike Light",
					"Sauce Labs Bolt T-Shirt"
					};
			
			home.addMultipleProducts(items);
			Assert.assertEquals(home.getCartCount(), "3");
			home.clickOncart();
			
			CheckOutPage checkout= new CheckOutPage(getDriver());
			checkout.clickonContinueShopping();
			Assert.assertTrue(home.isProductPageDisplayed());
				
		}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}

//ChekOut Items