package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver,String testname) {

		File dir= new File("./screenshots");
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		String path= System.getProperty("user.dir") + "/screenshots/" + testname + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);

		try {

			FileHandler.copy(src, dest);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
