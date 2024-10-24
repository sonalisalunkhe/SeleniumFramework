package Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements  IRetryAnalyzer{

	int  limit=1;
	int c=0;
	@Override
	public boolean retry(ITestResult result) {

		if(c<limit) {
			c++;
			return true;
		}
		return false;
	}

}
