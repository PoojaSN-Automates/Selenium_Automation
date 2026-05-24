package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.HomePage;
import pages.LoginPage;

public class ProductTest extends Testbase{

	@Test
	public void verifyProductpage() {
	LoginPage login = new LoginPage(Testbase.getDriver());
	
	try {
		HomePage home= login.verifyLogin("standard_user", "secret_sauce");
		
		Assert.assertTrue(home.isProductPageDisplayed());
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}