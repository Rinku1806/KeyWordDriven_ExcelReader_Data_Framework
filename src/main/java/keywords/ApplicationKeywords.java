package keywords;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords{
	
	public ApplicationKeywords() {
		String path  = System.getProperty("user.dir")+"//src//test//resources//env.properties";
		prop = new Properties();
		envProp = new Properties();
		logg = LogManager.getLogger();
		try {
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
			String env=prop.getProperty("env")+".properties";
			path  = System.getProperty("user.dir")+"//src//test//resources//"+env;
			fs = new FileInputStream(path);
			envProp.load(fs);
			String log_path  = System.getProperty("user.dir")+"//src//test//resources//log4j2.properties";
			//System.out.println(log_path);
			Configurator.initialize(null, log_path);
			logg.info("Configuration & Log configuration completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softAssert = new SoftAssert();
		
	}		

	public void defaultLogin() {		
		//navigate("TEST_URL");
		//wait(5);		
	}
	
	public void setReport(ExtentTest test) {
		this.test=test;
	}

	public void Login(String username, String Password) {	
		clear("USERNAME_INPUT_xpath");
		type("USERNAME_INPUT_xpath", username);
		clear("PASSWORD_INPUT_xpath");
		type("PASSWORD_INPUT_xpath", Password);
		//wait(2);
		click("LOGIN_BUTTON_xpath");
		//waitForPageToLoad();
		//wait(2);
		
	}
	
	public Logger getlogger(Logger logt) {
		return logg;
		
	}


	public void returnToMainMenu(String RTMButton) {
		click(RTMButton);		
	}

	public void openbrowser() {
		// TODO Auto-generated method stub
		
	}

	public void verifyTableEntitites(String object, String data) {
		WebElement table  = getElement(object);		
		String[] Expected = envProp.getProperty(data).split(","); 		
		List<WebElement> trows = table.findElements(By.tagName("tr"));
		Map<String, String> valueStore = new HashMap<String, String>();
		for (int i = 0; i < trows.size(); i++) {
			List<WebElement> temp = trows.get(i).findElements(By.tagName("td"));
			String[] temps = new String[2];
			for (int j = 0; j < temp.size(); j++) {
				temps[j] = temp.get(j).getText();
				valueStore.put(temps[0], temps[1]);
			}
		}
		for (int k = 0; k < Expected.length; k++) {
			test.info("Validating available pets in the given corresponding tables");
			softAssert.assertEquals(valueStore.get(valueStore.keySet().toArray()[k]), Expected[k]);
		}
		
	}
	
	public void verifyDashBoardContent(String object, String data) {
		WebElement table  = getElement(object);		
		String [] DashBoardActualText = table.getText().strip().split("\\n");
		List<String> finalDashBoardText = new ArrayList<String>(); 
		for (int i = 0; i < DashBoardActualText.length; i++) {
			if(DashBoardActualText[i]!="") {
			finalDashBoardText.add(DashBoardActualText[i]);
			}
		}
		
		String[] Expected = envProp.getProperty(data).split(";");
		
		for (int k = 0; k < Expected.length; k++) {
			System.out.println(finalDashBoardText.get(k)  +"-------"+ Expected[k]);
			softAssert.assertEquals(finalDashBoardText.get(k), Expected[k]);			
		}		
	}
	

	public void waitTime() {
		// TODO Auto-generated method stub
		
	}


}
