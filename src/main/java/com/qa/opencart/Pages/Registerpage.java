package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementsUtils;
import com.qa.opencart.constants.AppConstants;

public class Registerpage {
	
	private final By firstName=By.id("input-firstname");
	private final By lastname=By.id("input-lastname");
	private final By email=By.id("input-email");
	private final By telephone=By.id("input-telephone");
	private final By password=By.id("input-password");
	private final By confirmpass=By.id("input-confirm");
	
	private final By subcribeYes=By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[1]");
	private final By subcribeNo=By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[2]");
	
	private final By privacy =By.xpath("//input[@name='agree']");
	
	private final By continuebutton=By.xpath("//input[@value='Continue']");
	
	private final By successmsg=By.tagName("h1");
	
	private WebDriver driver;

	private ElementsUtils elutils;

	public Registerpage(WebDriver driver) {

	    this.driver = driver;
	    elutils = new ElementsUtils(driver);

	}
	
	public void userRegistration(String Fname,String Lname,String Email,String phone,String Pass,String Subscribe) {
		
		elutils.waitForElementPresence(firstName, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(Fname);
		elutils.dosendkeys(lastname, Lname);
		elutils.dosendkeys(email, Email);
		elutils.dosendkeys(telephone, phone);
		elutils.dosendkeys(password, Pass);
		elutils.dosendkeys(confirmpass, Pass);
		
		if(Subscribe.equals("yes")) {
			elutils.doClick(subcribeYes);
		}
		else {
			elutils.doClick(subcribeNo);
		}
		
		elutils.doClick(privacy);
		
		elutils.doClick(continuebutton);
		
		
	}
	
	public String successmsg() {
		
		String successtext=elutils.waitForElementPresence(successmsg, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Sucess msg is : "+successtext);
		return successtext;
	}

}
