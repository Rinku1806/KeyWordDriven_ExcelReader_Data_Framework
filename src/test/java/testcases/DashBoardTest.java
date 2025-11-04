package testcases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;

import io.opentelemetry.sdk.metrics.data.Data;
import util.Xls_Reader;
import utilties.RetryTestAnalyzer;

public class DashBoardTest extends testbase.BaseTest {

	@Test //(retryAnalyzer = RetryTestAnalyzer.class)
	public void verifyAvailablePets(ITestContext context) throws InterruptedException {
		ds.log("Starting to create a portfolio");

		@SuppressWarnings("unchecked")
		HashMap<String, String> dataMap = (HashMap<String, String>) context.getAttribute("data");
		String runmode = dataMap.get("runmode");

		if (runmode == "N") {
			throw new SkipException("In Excel Sheet it is set to No");
		}

		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\Testcases.xlsx");
		ds.executeTest(xls, "TestCases", "verifyAvailablePets");// pass the data

	}

	@Test // (retryAnalyzer = RetryTestAnalyzer.class)
	public void verifyAllPets() throws InterruptedException {
		System.out.println("reached inside the test");
		ds.log("Starting to create a portfolio");
		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\Testcases.xlsx");
		ds.executeTest(xls, "TestCases", "verifyAllPets");// pass the data

	}
}
