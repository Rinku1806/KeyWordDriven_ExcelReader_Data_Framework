package listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import utilties.RetryTestAnalyzer;

public class RetryListener implements IAnnotationTransformer {
	
	
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod ) {
		
		testAnnotation.setRetryAnalyzer(RetryTestAnalyzer.class);
	}	
	

}
