package testcases;

import org.testng.annotations.Test;

import util.Xls_Reader;
import utilties.RetryTestAnalyzer;

public class StoreEntryTest extends testbase.BaseTest {

	@Test//(retryAnalyzer = RetryTestAnalyzer.class)
	public void VerifySignInButtonPresence() throws InterruptedException {
		System.out.println("reached inside the test");
		ds.log("Starting to create a portfolio");
		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\Testcases.xlsx");
		ds.executeTest(xls, "TestCases", "VerifySignInButtonPresence");// pass the data

	}

	@Test //(retryAnalyzer = RetryTestAnalyzer.class)
	public void VerifyStoreEntryTitle() throws InterruptedException {
		System.out.println("reached inside the test");
		ds.log("Starting to create a portfolio");
		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\Testcases.xlsx");
		ds.executeTest(xls, "TestCases", "VerifyStoreEntryTitle");// pass the data

	}

	@Test //(retryAnalyzer = RetryTestAnalyzer.class)
	public void Verifylogin() throws InterruptedException {
		System.out.println("reached inside the test");
		ds.log("Starting to create a portfolio");
		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\Testcases.xlsx");
		ds.executeTest(xls, "TestCases", "Verifylogin");// pass the data

	}

}
