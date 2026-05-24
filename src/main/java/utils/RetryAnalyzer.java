package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer  {

	int count=0;
	int maxRetry=2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxRetry) {
			count++;
			System.out.println("Retrying test: "+result.getName()+ " (" + count + ")");
			return true;
		}
		return false;
	}

}
