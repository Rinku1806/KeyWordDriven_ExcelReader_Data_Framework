package driver;

import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentTest;

import keywords.ApplicationKeywords;
import util.Xls_Reader;

public class DriverScript {
	ApplicationKeywords app;
	Map<String, String> testData;
	ITestContext context;
	public static ExtentTest test;

	public DriverScript() {
		app = new ApplicationKeywords();
	}

	public static void main(String[] arg) throws InterruptedException {
		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testcases\\Testcases.xlsx");
		DriverScript ds = new DriverScript();
		ds.setReport(test);
		ds.executeTest(xls, "TestCases", "CreatePortfolio");

	}

	public void executeTest(Xls_Reader xls, String sheet, String testName) throws InterruptedException {

		int rows = xls.getRowCount(sheet);

		for (int rNum = 2; rNum <= rows; rNum++) {
			String tcid = xls.getCellData(sheet, "TCID", rNum);
			if (tcid.equalsIgnoreCase(testName)) {
				String keyword = xls.getCellData(sheet, "Keyword", rNum);
				String object = xls.getCellData(sheet, "Object", rNum);
				String dataKey = xls.getCellData(sheet, "Data", rNum);
				String data = null;
				if (!dataKey.equals("")) {
					System.out.println("xx" + dataKey + "xx");
					if (dataKey.equals("browserName") || dataKey.equals("username") || dataKey.equals("password")) {
						data = (String) testData.get(dataKey);
					} else {
						data = dataKey;
					}
				}
				String storVal = xls.getCellData(sheet, "StorVal", rNum);
				String stop = xls.getCellData(sheet, "StopOnFailure", rNum);

				if (!dataKey.equals("")) {
					System.out.println(tcid + " -- " + keyword + " -- " + object + " -- " + dataKey + " -- " + data
							+ " -- " + storVal);
				} else {
					System.out.println(tcid + " -- " + keyword + " -- " + object + " -- " + dataKey + " -- " + data
							+ " -- " + storVal);
				}
				if (keyword.equals("click"))
					app.click(object);
				else if (keyword.equals("openbrowser"))
					app.openBrowser(data);
				else if (keyword.equals("navigate"))
					app.navigate(data);
				else if (keyword.equals("clear"))
					app.clear(object);
				else if (keyword.equals("type"))
					app.type(object, data);
				else if (keyword.equals("wait"))
					app.wait(data);
				else if (keyword.equals("validateText"))
					app.validateText(object, data);
				else if (keyword.equals("selectByVisibleText"))
					app.selectByVisibleText(object, data);
				else if (keyword.equals("verifyTableEntitites"))
					app.verifyTableEntitites(object, data);
				else if (keyword.equals("returnToMainMenu"))
					app.returnToMainMenu(object);
				else if (keyword.equals("validateElementPresent"))
					app.validateElementPresent(object, Boolean.parseBoolean(dataKey));
				else if (keyword.equals("clickEnterButton"))
					app.clickEnterButton(object);
				else if (keyword.equals("validateTitle")) {
					app.validateTitle(data);
				} else if (keyword.equals("verifyDashBoardContent")) {
					app.verifyDashBoardContent(object, data);
				}
			}

		}

	}

	public void setReport(ExtentTest test) {
		app.setReport(test);
	}

	public void defaultLogin(String browser) {
		app.openBrowser(browser);
		app.defaultLogin();

	}

	public void quit() {
		if (app != null)
			app.quit();

	}

	public void setTestData(Map<String, String> data) {
		testData = data;

	}

	public void log(String logMessage) {
		app.log(logMessage);

	}

	public void setTestContext(ITestContext context) {
		this.context = context;
		app.setTestContext(context);

	}

}
