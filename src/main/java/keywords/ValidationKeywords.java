package keywords;

import org.openqa.selenium.support.ui.Select;

public class ValidationKeywords extends GenericKeywords {
	
	public void validateTitle(String expected) {
		log("Validating title");
		softAssert.assertEquals(getPageTitle(), envProp.getProperty(expected));
	}
	
	public void validateText(String actual, String expected) {
		
		softAssert.assertEquals(getText(actual),envProp.getProperty(expected));
		
	}
	
	public void validateElementPresent(String locator, boolean expected) {
		// failure
		boolean result  = isElementPresent(locator);
		softAssert.assertEquals(result,expected);
		//reportFailure("Element not found "+ locator,true);
	}
	
	public void validateLogin() {
		
	}


}
