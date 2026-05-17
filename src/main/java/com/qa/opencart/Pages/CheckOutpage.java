package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementsUtils;
import com.qa.opencart.constants.AppConstants;

public class CheckOutpage {
	
	
	
	private final By Headervalue=By.xpath("(//h1)[1]");
	
	private final By pagetext=By.xpath("(//h2)[1]");
	
private WebDriver driver;
	
	private ElementsUtils elutils;
	
	public CheckOutpage(WebDriver driver) {
		this.driver=driver;
		
		 elutils = new ElementsUtils(driver);

	}
	
	public String geTHeadervalue() {
		String text=elutils.waitForElementPresence(Headervalue, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("HeaderText is ; "+text);
		return text;
	}
	
	public String Getpagetext() {
		String textpage=elutils.waitForElementPresence(pagetext, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Page text is : "+textpage);
		return textpage;
	}
	

}
