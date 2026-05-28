package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class CartTest extends Testbase{
	
	ConfigReader config = new ConfigReader();
	
	@Test
	public void verifySingleProductCartCount() {
		
		
		LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
			home.addToCart("Sauce Labs Backpack");
			Assert.assertEquals(home.getCartCount(), "1");
			
			home.clickOncart();
			home.removeProduct("Sauce Labs Backpack");
			Assert.assertEquals(home.getCartCount(), "0");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void verifyMulipleProductsCartCount() {
		LoginPage login= new LoginPage(Testbase.getDriver());
		try {
			HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
			String [] items= {"Sauce Labs Backpack",
					"Sauce Labs Bike Light",
					"Sauce Labs Bolt T-Shirt"
					};
			home.addMultipleProducts(items);
			Assert.assertEquals(home.getCartCount(), "3");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

//To verify addtocart and verify badge of single and multiple items on cart