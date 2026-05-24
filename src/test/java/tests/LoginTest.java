package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Testbase;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtil;
import utils.TestResultManager;


public class LoginTest extends Testbase {
	
	@DataProvider(name="excelData",parallel=true)
	public Object[][] getData(){
		
		 System.out.println("DataProvider running");

		    Object [][] data = ExcelUtil.getTestData("Login");

		    System.out.println("Rows: " + data.length);

		    for (int i = 0; i < data.length; i++) {
		        System.out.println("Row " + i + ": " 
		            + data[i][0] + " | "
		            + data[i][1] + " | "
		            + data[i][2]);
		    }

		    Object[][] newData = new Object[data.length][4];

		    for (int i = 0; i < data.length; i++) {
		        newData[i][0] = i + 1;
		        newData[i][1] = data[i][0];
		        newData[i][2] = data[i][1];
		        newData[i][3] = data[i][2];
		    }

		    return newData;
				
	}
	
	@Test(dataProvider="excelData")
	public void verifyLogin(int rowNum,String username, String password,String expected) throws InterruptedException {
	
	TestResultManager.setRow(rowNum);
	
	LoginPage lg= new LoginPage(Testbase.getDriver());
	
	System.out.println("Test method running");
	
	HomePage homePage = lg.verifyLogin(username, password);
	
	System.out.println("Successsful login");
	
	boolean actual;
	
	if (expected.equalsIgnoreCase("success")) {
        actual = homePage.isLoginSuccessfull();
    } else {
        actual = lg.isErrorDisplayed();
    }

    Assert.assertTrue(actual);
	
	}
}
