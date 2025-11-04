package testbase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import driver.DriverScript;
import reports.ExtentManager;
import utilties.ReadingXLS;

public class BaseTest {
	public DriverScript ds;

	public ExtentReports rep;
	public ExtentTest test;
	Map<String, String> xmlParams;
	Map<String, String> xlPara;

	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context)
			throws NumberFormatException, FileNotFoundException, IOException, ParseException {
		System.out.println("----------Before Test---------");
		xmlParams = new HashMap<>();
		for (String name : context.getCurrentXmlTest().getAllParameters().keySet()) {
			xmlParams.put(name, context.getCurrentXmlTest().getParameter(name));
			// System.out.println(name + " = " +
			// context.getCurrentXmlTest().getParameter(name));
		}

		System.out.println("----------Before Test---------");

		xlPara = new ReadingXLS().getTestDataAsMap(xmlParams.get("suitename"), xmlParams.get("runmode"),
				(Integer.parseInt(xmlParams.get("iteration"))), xmlParams.get("datafilpath"));
		context.setAttribute("data", xlPara);
		String runMode = (String) xlPara.get("runmode");

		// Initialize Extent Reporting system
		rep = ExtentManager.getReports();
		test = rep.createTest(context.getCurrentXmlTest().getName());
		test.log(Status.INFO, "Starting Test " + context.getCurrentXmlTest().getName());
		test.log(Status.INFO, "Data " + xlPara.toString());

		context.setAttribute("report", rep);
		context.setAttribute("test", test);

		if (!runMode.equals("Y")) {
			System.out.println("Here it is set as No in base class");
			//test.log(Status.SKIP, "Skpping as Data Runmode is N");
			throw new SkipException("Skpping as Data Runmode is N");
		}

		try {

			ds = new DriverScript();
			ds.setReport(test);
			ds.log("Starting test " + context.getCurrentXmlTest().getName());
			ds.setTestData(xlPara);
			ds.setTestContext(context);
			context.setAttribute("driver", ds);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		System.out.println("****Before Method****");
		test = (ExtentTest) context.getAttribute("test");

		String criticalFailure = (String) context.getAttribute("criticalFailure");
		if (criticalFailure != null && criticalFailure.equals("Y")) {
			test.log(Status.SKIP, "Critical Failure in Prevoius Tests");
			throw new SkipException("Critical Failure in Prevoius Tests");// skip in testNG
		}
		ds = (DriverScript) context.getAttribute("driver");
		rep = (ExtentReports) context.getAttribute("report");

	}

	@AfterTest(alwaysRun = true)
	public void quit(ITestContext context) {
		ds = (DriverScript) context.getAttribute("driver");
		if (ds != null)
			ds.quit();

		rep = (ExtentReports) context.getAttribute("report");

		if (rep != null)
			rep.flush();

		File sourceDir = new File(System.getProperty("user.dir") + "//XtentReports//" + ExtentManager.ForLatest);
		File targetDir = new File(System.getProperty("user.dir") + "//XtentReports//Latest");

		if (targetDir.isDirectory() && targetDir.listFiles().length > 0) {
			try {
				FileUtils.cleanDirectory(targetDir);
				System.out.println("Directory was not empty and contents deleted.");
			} catch (IOException e) {
				System.err.println("Error cleaning directory: " + e.getMessage());
			}
		}

		try {
			FileUtils.copyDirectory(sourceDir, targetDir);
			System.out.println("Folder contents copied successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
