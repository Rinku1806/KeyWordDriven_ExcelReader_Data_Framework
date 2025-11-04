package utilties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private static final int max_Retries = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (!result.isSuccess()) {
			if (count < max_Retries) {
				count++;
				return true;
			}
		}

		return false;
	}

}
