package tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Testbase;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsSortPage;

public class ProductsSortTest extends Testbase{

	@Test
	public void verifyallSortingItems() {
		
		LoginPage login= new LoginPage(Testbase.getDriver());
		
		try {
			
			HomePage home= login.verifyLogin("standard_user", "secret_sauce");
			
			ProductsSortPage producsort= new ProductsSortPage(getDriver());
			Thread.sleep(2000);
			List<String> actualmenuitems= producsort.getallProductSorting();
			
			List<String> expectedSorting= Arrays.asList(
					"Name (A to Z)",
					"Name (Z to A)",
					"Price (low to high)",
					"Price (high to low)");
			
			Assert.assertEquals(actualmenuitems, expectedSorting); 
			System.out.println("All Sorting displayed");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
    public void verifyNameAToZSorting()
            throws InterruptedException {

		LoginPage login= new LoginPage(Testbase.getDriver());

        HomePage home= login.verifyLogin("standard_user", "secret_sauce");

        ProductsSortPage sort= new ProductsSortPage(getDriver());

        sort.selectSorting("Name (A to Z)");

        Assert.assertTrue(sort.verifyNameAToZ());

        System.out.println("Name A-Z sorting working");
    }



    @Test
    public void verifyNameZToASorting()
            throws InterruptedException {

    	LoginPage login= new LoginPage(Testbase.getDriver());

        HomePage home= login.verifyLogin("standard_user", "secret_sauce");

        ProductsSortPage sort= new ProductsSortPage(getDriver());


        sort.selectSorting("Name (Z to A)");
        
        Assert.assertTrue(sort.verifyNameZToA());

        System.out.println(
                "Name Z-A sorting working");
    }



    @Test
    public void verifyPriceLowToHighSorting()
            throws InterruptedException {

    	LoginPage login= new LoginPage(Testbase.getDriver());

        HomePage home= login.verifyLogin("standard_user", "secret_sauce");

        ProductsSortPage sort= new ProductsSortPage(getDriver());

        sort.selectSorting("Price (low to high)");

        Assert.assertTrue(
                sort.verifyPriceLowToHigh());

        System.out.println("Price Low-High sorting working");
    }



    @Test
    public void verifyPriceHighToLowSorting()
            throws InterruptedException {

    	LoginPage login= new LoginPage(Testbase.getDriver());

        HomePage home= login.verifyLogin("standard_user", "secret_sauce");

        ProductsSortPage sort= new ProductsSortPage(getDriver());


        sort.selectSorting("Price (high to low)");

        Assert.assertTrue(sort.verifyPriceHighToLow());

        System.out.println("Price High-Low sorting working");
    }


}
