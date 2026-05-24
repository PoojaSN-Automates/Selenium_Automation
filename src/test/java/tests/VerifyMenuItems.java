package tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;


public class VerifyMenuItems extends Testbase{

	@Test
	public void verifyallMenuItems() {
		
		LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			
			HomePage home= login.verifyLogin("standard_user", "secret_sauce");
			
			MenuPage menu= new MenuPage(getDriver());
			Thread.sleep(2000);
			menu.clickOnMenu();
			List<String> actualmenuitems= menu.getmenuItems(); 
			
			List<String> expectedmenuItems= Arrays.asList(
					"All Items",
					"About",
					"Logout",
					"Reset App State");
			
			Assert.assertEquals(actualmenuitems, expectedmenuItems); 
			System.out.println("All menu items displayed");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//To verify Logout
	
	@Test
public void verifylogout() {
		
		LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			
			HomePage home= login.verifyLogin("standard_user", "secret_sauce");
			
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
