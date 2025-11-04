package testcases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import util.Xls_Reader;
import utilties.RetryTestAnalyzer;

//import utilities.DataUtil;

public class HomePageTest extends testbase.BaseTest {

	@Test //(retryAnalyzer = RetryTestAnalyzer.class)
	public void VerifyHomePageTitle(ITestContext context) throws InterruptedException {
		
		
		System.out.println("reached inside the Hompage test");
		ds.log("Starting to create a portfolio");
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\resources\\testcases\\Testcases.xlsx");
		ds.executeTest(xls, "TestCases", "VerifyHomePageTitle");//pass the data
		
		
	}

}
