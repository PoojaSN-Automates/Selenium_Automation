package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class ProductTest extends Testbase{
	
	 ConfigReader config = new ConfigReader();

	@Test
	public void verifyProductpage() {
	LoginPage login = new LoginPage(Testbase.getDriver());
	
	try {
		HomePage home= login.verifyLogin(config.getUsername(),config.getPassword());
		
		Assert.assertTrue(home.isProductPageDisplayed());
		 System.out.println("Product page displayed");
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}