package common;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {
	
	private ExtentTest test;
	
	public void pass(String text){
		System.out.println(text);
		this.test.log(LogStatus.PASS, text);
	}
	
	public void error(String text){
		System.out.println(text);
		this.test.log(LogStatus.ERROR, text);
	}
	
	public void info(String text){
		System.out.println(text);
		this.test.log(LogStatus.INFO, text);
	}

	public ExtentTest getTest() {
		return test;
	}

	public void setTest(ExtentTest test) {
		this.test = test;
	}

}
