package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		if(extent==null) {
			
			 // Timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            

            // Report path
            String reportPath = "./reports/ExtentReport.html";

            // Spark Reporter
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            // Report Config
            reporter.config().setReportName("SauceDemo Automation Report");
            reporter.config().setDocumentTitle("Automation Test Results");

            reporter.config().setOfflineMode(true);
            reporter.config().setTimelineEnabled(true);
            // Extent Reports
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // System Info
            extent.setSystemInfo("Project","SauceDemo");
            extent.setSystemInfo("Tester","Sira");
            extent.setSystemInfo("Browser","Chrome");
            extent.setSystemInfo("Framework","Selenium + TestNG");
            extent.setSystemInfo("Environment","QA");
        }
			
			/*ExtentSparkReporter reporter= new ExtentSparkReporter("./reports/extentReports.html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);*/
		
		return extent;
		
	}
	

}
